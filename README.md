# eclipselinkissue
During upgrading from eclipse link 2.5.2 to 2.7.4 we found some issues

## Table create code
```sql
CREATE TABLE `Parent` (
	`IdParent` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`Name` VARCHAR(50) NOT NULL,
	`RecordVersion` BIGINT(20) NOT NULL,
	PRIMARY KEY (`IdParent`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;


CREATE TABLE `Child` (
	`IdChild` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`Name` VARCHAR(255) NOT NULL,
	`RecordVersion` BIGINT(20) NOT NULL,
	PRIMARY KEY (`IdChild`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;

CREATE TABLE `ParentChildrens` (
	`IdParent` BIGINT(20) NOT NULL,
	`IdChild` BIGINT(20) NOT NULL,
	PRIMARY KEY (`IdParent`, `IdPChild`),
	CONSTRAINT `ParentChildrens_ibfk_1` FOREIGN KEY (`IdParent`) REFERENCES `Parent` (`IdParent`),
	CONSTRAINT `ParentChildrens_ibfk_2` FOREIGN KEY (`IdChild`) REFERENCES `Child` (`IdChild`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;

CREATE TABLE `Main` (
	`IdMain` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`IdParent` BIGINT(20) NOT NULL,
	`Name` VARCHAR(255) NOT NULL,
	`RecordVersion` BIGINT(20) NOT NULL,
	PRIMARY KEY (`IdMain`),
	CONSTRAINT `Main_ibfk_1` FOREIGN KEY (`IdParent`) REFERENCES `Parent` (`IdParent`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
AUTO_INCREMENT=33144
;
```

## Table fill code


```sql

INSERT INTO `parent` (`IdParent`, `Name`, `RecordVersion`) VALUES (1, 'Test1', 0);
INSERT INTO `parent` (`IdParent`, `Name`, `RecordVersion`) VALUES (2, 'Test2', 0);

INSERT INTO `child` (`IdChild`, `Name`, `RecordVersion`) VALUES (1, 'Child1', 0);
INSERT INTO `child` (`IdChild`, `Name`, `RecordVersion`) VALUES (2, 'Child2', 0);

INSERT INTO `parentchildrens` (`IdParent`, `IdChild`) VALUES (1, 1);
INSERT INTO `parentchildrens` (`IdParent`, `IdChild`) VALUES (2, 1);
INSERT INTO `parentchildrens` (`IdParent`, `IdChild`) VALUES (1, 2);

INSERT INTO `main` (`IdMain`, `IdParent`, `Name`, `RecordVersion`) VALUES (1, 1, 'Main1', 0);
INSERT INTO `main` (`IdMain`, `IdParent`, `Name`, `RecordVersion`) VALUES (2, 2, 'Main2', 0);


```
## Domain XML config (connection pools)
```xml
<jdbc-connection-pool datasource-classname="com.mysql.jdbc.jdbc2.optional.MysqlXADataSource" name="mysqlTestPool" res-type="javax.sql.XADataSource">
      <property name="serverName" value="localhost"/>
      <property name="databaseName" value="test"/>
      <property name="portNumber" value="3306"/>
      <property name="useUnicode" value="true"/>
      <property name="characterEncoding" value="utf8"/>
      <property name="password" value="PUT YOUR PASSWORD HERE"/>
      <property name="user" value="PUT YOUR USER HERE"/>
</jdbc-connection-pool>
<jdbc-resource jndi-name="mysql/test" pool-name="mysqlTestPool"/>

<servers>
    <server config-ref="server-config" name="server">
      .........................
	  <resource-ref ref="mysql/test"/>
      .........................
    </server>
  </servers>
```	
