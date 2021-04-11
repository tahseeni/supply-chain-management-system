# supply-chain-management-system
ENSF409 Final Project - Group 32
Authors: Nabeel Amjad, Stalin D. Cunha, Tahseen Intesar, Gurpartap Sohi

# How to run the program
1. Using Command Line, navigate to the project folder containing edu and lib directories.
2. Compile the java files using the classpath: `javac -cp .;lib\* edu\ucalgary\ensf409\*.java`.
3. Execute `SupplyChainOperator` : `java -cp .;lib\* edu.ucalgary.ensf409.SupplyChainOperator`.
4. Upon execution, the username and password for the MySQL database will be prompted.

# How to run the tests
1. Using Command Line, navigate to the project folder containing edu and lib directories.
2. Compile the java files using the classpath: `javac -cp .;lib\* edu\ucalgary\ensf409\*.java`
3. Open the MySQL command line client and execute the command `source path\inventory.sql;` ,
where path is the pathname to the directory in which "inventory.sql" is located.
4. Run `SupplyChainOperatorTest`: `java -cp .;lib\* org.junit.runner.JUnitCore edu.ucalgary.ensf409.SupplyChainOperatorTest`.
5. Run `DatabaseConnectorTest`: `java -cp .;lib\* org.junit.runner.JUnitCore edu.ucalgary.ensf409.DatabaseConnectorTest`.
6. Repeat step 3 to restore the database.
