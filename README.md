# Java REST Service example

> In this repository, you are going to find, an example of web services based on Java that interacts with a Maria Database through hibernate ORM

## Requirements

- [MariaDB](https://mariadb.org/download/) to storage the database
- Netbeans 8.2 (I developed this project in Netbeans 8.2 but, you can use other IDE)
- Any REST client (Don't you know what it is? [hit here](https://www.guru99.com/rest-client-testing.html#:~:text=REST%20Client%20is%20a%20method,is%20called%20the%20REST%20client.))

## Installation

- Clone this repository
- The _SQL script_ of the database is inside the _db_ folder.
- Runs the _SQL script_ in the MariaDB client
- Creates a new user in the table _USER_
- Opens the Netbeans project _server_
- Modifies the _hibernate.cfg.xml_ file
    - _hibernate.connection.url_, it contains the URL of the host, the port, and the name of the database
    - _hibernate.connection.username_, it contains the username to access the database
    - _hibernate.connection.password_, it contains the password to access the database
- Finally, run the project

## Try it

At this point, you will be able to use the endpoints
- _GET_ path
- _POST_ new position
- _POST_ new destiny

I also shared the workspace of [Insomnia](https://insomnia.rest/download/#windows) (it is a REST Cient). So, you can try the web services.

## Intregation

I developed a SPA to use this web services as backend. You can find the angular project [here](https://github.com/locus0002/markers-n-polylines)
