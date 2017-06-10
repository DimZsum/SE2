# Ein Java-Beispielprojekt für die Lehrveranstaltungen "Software Engineering 2" (SE2) und "Softwarequalität und Test" (SwQT).

## Projekt-Voraussetzungen

- Versionsverwaltungswerkzeug "Git"
- Installiertes Java 8 JDK (mit Dokumentation)
- Eine Java-Entwicklungsumgebung ("Eclipse", "JetBrains IntelliJ IDEA", "NetBeans" o.ä.)
- JEE-Application Server "JBoss WildFly Version 8.2.1-final"
- Build-System "maven" (optional)
- Ein relationales Datenbankverwaltungssystem mit JDBC-Anbindung ("MySQL" empfohlen, oder "MariaDB", "PostgreSQL", "Oracle Express" etc.)
- Ein REST-Client (empfohlen "Advanced Rest Client Application" für Chrome)

## Java-Projekt herunterladen

In einem beliebigen Verzeichnis ausführen:

> git clone https://git.ziemers.de/edu/swXercise.git

Danach das entstandene Projektverzeichnis swXercise in der Entwicklungsumgebung als existierendes Maven-Projekt importieren.

## MySQL konfigurieren

Zunächst muss das MySQL-Datenbanksystem installiert werden. Dies ist betriebssystemspezifisch. Während der Installation wird ein temporäres Root-Kennwort generiert.

Dann kann das MySQL-Frontend gestartet werden (es wird zunächst nach dem temporären Kennwort gefragt):

> mysql -u root -p

Dann kann/muss das Kennwort geändert werden, beispielsweise in 'root':

> ALTER USER 'root'@'localhost' IDENTIFIED BY ‘root‘;

Dann müssen die beiden projektspezifischen Datenbanken (für Test und "Produktion") erstellt und mit den nötigen Rechten versehen werden:

> create database swxercise;

> create database swxercise_test;

> grant all privileges on swxercise.* to 'root'@'localhost' identified by 'root';

> grant all privileges on swxercise_test.* to 'root'@'localhost' identified by 'root';

## MySQL-Datenbank im WildFly einbinden

Zunächst muss der JBoss WildFly Application Server installiert werden. Dies ist betriebssystemspezifisch.

### Datei module.xml im Verzeichnis $JBOSS_HOME/modules/system/layers/base/com/mysql/driver/main erstellen mit folgendem Inhalt:

```
<module xmlns="urn:jboss:module:1.3" name="com.mysql.driver">
  <resources>
    <resource-root path="mysql-connector-java-5.1.41-bin.jar" />
  </resources>
  <dependencies>
    <module name="javax.api"/>
    <module name="javax.transaction.api"/>
  </dependencies>
</module>
```

Und natürlich die jar-datei mit dem angegebenen MySQL-Treiber in dieses Verzeichnis hineinkopieren. Er muss von den Oracle-Internetseiten heruntergeladen werden.

Aufgepasst bei der Version des MySQL-Konnektors in der module.xml!

### In der standalone.xml im Verzeichnis $JBOSS_HOME/standalone/configuration folgenden Inhalt unterhalb der WildFly-Beispieldatenbank com.h2database.h2 ergänzen:

```
<drivers>
  ... (hier befindet sich der H2-Treiber; unserer kommt darunter)
  <driver name="mysql" module="com.mysql.driver">
    <driver-class>com.mysql.jdbc.Driver</driver-class>
  </driver>
</drivers>
```

## MySQL-Datasource im WildFly definieren

### In der standalone.xml im Verzeichnis $JBOSS_HOME/standalone/configuration folgenden Inhalt innerhalb der Datasources ergänzen:

```
... (hier befindet sich die H2-Datasource; unsere kommt darunter)
</datasource>
<datasource jndi-name="java:jboss/datasources/swXerciseDS" pool-name="swXerciseDS" enabled="true" use-java-context="true" jta="true">
  <connection-url>jdbc:mysql://localhost:3306/swxercise?useSSL=false</connection-url>
  <driver>mysql</driver>
  <!-- der Default-Eintrag "TRANSACTION_REPEATABLE_READ" kann zu Camunda-Deadlocks kommen (ThZ, 24.04.2017) -->
  <transaction-isolation>TRANSACTION_READ_COMMITTED</transaction-isolation>
  <pool>
    <min-pool-size>10</min-pool-size>
    <max-pool-size>100</max-pool-size>
    <prefill>true</prefill>
  </pool>
  <security>
    <user-name>root</user-name>
    <password>root</password>
  </security>
  <statement>
    <prepared-statement-cache-size>32</prepared-statement-cache-size>
    <share-prepared-statements>true</share-prepared-statements>
  </statement>
</datasource>
```

## JBoss WildFly in Eclipse einbinden

### JBoss Tools-Plugin installieren (nur für Eclipse)

- Auswählen "Help -> Eclipse Marketplace..."
- Find: "JBoss Tools (Neon)" (oder was halt gerade aktuell ist)
- "Install" klicken
- Auswählen "JBoss AS, Wildfly & EAP Server Tools"

Quelle: https://tools.jboss.org/downloads/jbosstools/neon/4.4.3.Final.html

### JBoss WildFly Server in Eclipse einbinden (nur für Eclipse)

- Auswählen "File -> New -> Other..."
- Auswählen "Server -> Server" (Next >)
- WildFly 8.x
  - Server's host name: localhost
  - Server name: WildFly 8.x beliebig wählen (Next >)
- "Create a new Server Adapter"
  - The server is: Local
  - Controlled by: Filesystem and shell Operations
  - Create new runtime (next page) (Next >)
- "New Server"
  - Name WildFly 8.x Runtime
  - Home Directory /home/<user>/wildfly-8.2.1.Final/ (oder das Verzeichnis, das Sie gewählt haben)
  - Execution Environment: JavaSE-1.8
  - Server base directory: standalone
  - Configuration file: standalone.xml (Next >)
  - "Add and Remove", das gewünschte Maven-Artefakt "swXercise" auswählen (Finish)

## JBoss WildFly in IntelliJ IDEA einbinden

### JBoss WildFly-Server initial bekanntmachen (nur für IntelliJ IDEA)

- IDE-Menü "Run -> Edit Configurations..."
  - "Defaults -> JBoss Server -> Local" und Reiter "Server"
    - "Configure..." und einen neuen JBoss Server mittels seines JBoss Home-Verzeichnisses angeben

### Run Configuration erstellen (nur für IntelliJ IDEA)

- Wiederum IDE-Menü "Run -> Edit Configurations..."
  - Plus-Symbol, um eine neue WildFly-Run Configuration zu definieren
  - "JBoss Server -> Local" auswählen
    - Reiter "Server" und den zuvor bekanntgemachten JBoss-Server wählen
    - Reiter "Deployment"
      - Selected artifacts will be deployed at server startup -> das gewünschte Maven-Artefakt "swXercise" auswählen, und zwar das mit "exploded" markierte; deployt als ausgepackte Ordnerstruktur und nicht als "WAR-Datei" (vorteilhaft beim Hot-Deployment)
