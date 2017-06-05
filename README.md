# Ein Java-Beispielprojekt für die Lehrveranstaltungen "Software Engineering 2" (SE2) und "Softwarequalität und Test" (SwQT).

## Projekt-Voraussetzungen

- Versionsverwaltungswerkzeug "Git"
- installiertes Java 8 JDK (mit Dokumentation)
- eine Java-Entwicklungsumgebung ("Eclipse", "JetBrains IntelliJ IDEA", "NetBeans" o.ä.)
- JEE-Application Server "JBoss WildFly Version 8.2.1-final"
- Plugin "JBoss Tools" (nur für Eclipse)
- Build-System "maven"
- ein relationales Datenbankverwaltungssystem ("MySQL" empfohlen, oder "MariaDB", "PostgreSQL", "Oracle Express" etc.)
- ein REST-Client (empfohlen "Advanced Rest Client Application" für Chrome)

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

Dann muss eine projektspezifische Datenbank erstellt und mit den nötigen Rechten versehen werden:

> create database swxercise;

> grant all privileges on swxercise.* to 'root'@'localhost' identified by 'root';

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