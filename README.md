# TrinacriaSQL

[![Donate](https://img.shields.io/badge/Donate-PayPal-orange.svg)](https://www.paypal.com/donate/?hosted_button_id=2JP9NMMAC5EHU)

TrinacriaSQL is an easy and straightforward interpreted SQL dialect that allows you to write simpler and more understandable queries in [Sicilian Language](https://en.wikipedia.org/wiki/Sicilian_language).

## Set up

TrinacriaSQL can be used either as a Java library or as a standalone SQL database client.

### Java library

To use it as a Java library, download the latest jar in the release section and import it into your project, along with the driver of the database you want to use. Then, you can use the methods exposed by the class `sc.alwe.TrinacriaSQL.TrinacriaSQLInterpreter` which allow you to either execute a TrinacriaSQL query against your database (to which you will provide a connection) or just translate it into plain old SQL.
TrinacriaSQL throws a `ChiFariException` with useful debugging information whenever an error occurs.

### Database client

To use TrinacriaSQL as a database client, download the latest jar in the release section and execute it with the command:

```text
java -cp trinacria-sql-1.0.0.jar;<path_to_your_db_driver> sc.alwe.TrinacriaSQL.TrinacriaSQLShell
```

The client will ask for a JDBC string representing the database to connect to (including credentials). After the connection is established, you can start running commands.

## Language basics

Before delving into the specific commands, it's important to consider some general rules to avoid mistakes:

- parenthesis are not valid characters in TrinacriaSQL queries. In the following examples, they are just used to distinguish between (mandatory parameters) and [optional parameters]
- TrinacriaSQL doesn't allow multi-line queries. Therefore, there's no end-of-query character (like ; in SQL)
- spacing is very important when using operators! A query using a condition `a = 5` will work but the same query with `a= 5` or `a =5` or `a=5` will not

## Data Manipulation Language

Likewise standard SQL, TrinacriaSQL allows performing data manipulation. Here's a list of supported operations:

### Retrieving data

To retrieve data, you can use the `pigghiamu` command. Here's the command syntax:

```text
pigghiamu (<comma_separated_column_names> || tuttu chiddu chi cc'è) chi veni da <table_name> [iunciuto paro paro <table_name>...] [unni <condition>]
```

The first argument for the `pigghiamu` command is the columns to retrieve. They can be specified either as a list of comma-separated values or with the `tuttu chiddu chi cc'è` which will return all the columns.

After the columns, the following parameter is the name of the table where to fetch the data with `chi veni da`. Data can be fetched from multiple tables by using the optional join operator `iunciuto paro paro` followed by another table name. Currently, there's no limit on the number of `iunciuto paro paro` that can be applied to a single `pigghiamu`. 

Finally, you can filter the rows using the optional `unni` clause, followed by one or more conditions. The conditions work exactly like in SQL, with a slightly different syntax for some operators (check the [Language Reference](#language-reference) section).

Here are some sample queries:

```text
pigghiamu tuttu chiddu chi cc'è chi veni da user; # retrieves all users' data
pigghiamu email chi veni da user unni id = 6 o name è nuddu; # retrieves all the emails of the users with id 6 or null name
pigghiamu email chi veni da user iunciuto paro paro city iunciuto paro paro account unni user.id = 6 e user.birth_city = city.id e user.account_id = account.id; # retrieves the data of the user with id 6 along joined with his birth city and his account data
```

### Deleting data

Data deletion can be performed using the `livamu tuttu` command which supports a subset of options from the `pigghiamu` command. Here's the syntax:

```text
livamu tuttu chi veni da <table_name> [unni <condition>]
```

Here are some sample queries:

```text
livamu tuttu chi veni da user; # deletes all users' data
livamu tuttu chi veni da user unni name nun è nuddu o deleted è true; # deletes the users with name not null or with deleted = true
```

### Updating data

The command `refacìemu` is used to update data in a table. The syntax is:

```text
refacìemu <table_name> mètti <column_1> accussì <value_1>, <column_2> accussì <value_2>, ... [unni <conditions>]
```

The `mètti` operator marks the begin of a list of column/values assignments using the assignment operator `accussì`.

Here are some sample queries:

```text
refacìemu user mètti name accussì "Pippo"; # sets the name "Pippo" for all the users
refacìemu user mètti name accussì "Pinco", surname accussì "Pallo" unni name è nuddu; # sets the name to "Pinco" and surname to "Pallo" for all users with null name
```

### Inserting data

Data insertion can be performed using the `mìettemu rintra` operator as following:

```text
mìettemu rintra <table_name> (<column_1>, <column_2>...) chisti <value_1>, <value_2>...
```

After the table name, you can specify a list of columns whose data are being inserted. If not present, TrinacriaSQL will default to all columns. The `chisti` keyword marks the beginning of a comma-separated list of values to insert. Each insert statement can only add one row.

Here are some sample queries:

```text
mìettemu rintra user chisti 1, "Pinco", "Pallo"; # inserts a new user with all his data
mìettemu rintra user name chisti "Pinco"; # inserts a new user with only his name set
```

## Transaction support

Being a fully ACID compliant language, TrinacriaSQL offers basic transaction management. To begin a transaction, you can issue the command `we compà`. You can then commit the transaction with the command `finemula ccà` or perform rollback with the command `turnamu nnarrè`.

## Language reference

Follows a table that roughly maps TrinacriaSQL language to standard SQL:

| TrinacriaSQL keyword  | SQL equivalent    | Valid in...            |
|-----------------------|-------------------|------------------------|
| pigghiamu             | SELECT            | SELECT                 |
| refacìemu             | UPDATE            | UPDATE                 |
| mìettemu              | INSERT            | INSERT                 |
| rintra                | INTO              | INSERT                 |
| livamu tuttu          | DELETE            | DELETE                 |
| iunciuto paro paro    | INNER JOIN        | SELECT                 |
| chi veni da           | FROM              | SELECT, DELETE         |
| tuttu chiddu chi cc'è | *                 | SELECT                 |
| comu                  | AS                | SELECT                 |
| unni                  | WHERE             | SELECT, UPDATE, DELETE |
| e                     | AND               | ANY WHERE CLAUSE       |
| o                     | OR                | ANY WHERE CLAUSE       |
| nuddu                 | NULL              | ANY WHERE CLAUSE       |
| è                     | IS                | ANY WHERE CLAUSE       |
| nun è                 | IS NOT            | ANY WHERE CLAUSE       |
| chisti                | VALUES            | INSERT                 |
| mètti                 | SET               | UPDATE                 |
| accussì               | = (assignment)    | UPDATE                 |
| >                     | >                 | ANY WHERE CLAUSE       |
| <                     | <                 | ANY WHERE CLAUSE       |
| = (comparison)        | = (comparison)    | ANY WHERE CLAUSE       |
| !=                    | !=                | ANY WHERE CLAUSE       |
| <>                    | <>                | ANY WHERE CLAUSE       |
| <=                    | <=                | ANY WHERE CLAUSE       |
| >=                    | >=                | ANY WHERE CLAUSE       |
| turnamu nnarrè        | ROLLBACK          | TRANSACTION            |
| finemula ccà          | COMMIT            | TRANSACTION            |
| we compà              | BEGIN TRANSACTION | TRANSACTION            |

## Supported Database

TrinacriaSQL has been extensively tested with MySQL and H2. Other databases may not work properly.

## Contributions

Improvements are always appreciated! If you want to contribute to this project though, remember to open an issue with your suggestion before doing any changes. This will help you avoid working on something that won't get merged.

## Contacts

You can contact me using the informations in my GitHub profile or opening an issue on this repository. I'll try to reply as soon as I can.

## License

The project is released under the MIT license, which lets you reuse the code for any purpose you want (even commercial) with the only requirement being copying this project license on your project.

<sub>Copyright (c) 2023 Daniele Nicosia</sub>
