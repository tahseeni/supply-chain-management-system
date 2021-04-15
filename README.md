# supply-chain-management-system
ENSF409 Final Project - Group 32
Authors: Nabeel Amjad, Stalin D Cunha, Tahseen Intesar, Gurpartap Sohi

# How to run the program
1. Using Command Line, navigate to the project folder containing edu and lib directories.
2. Compile the java files using the classpath: `javac -cp .;lib\* edu\ucalgary\ensf409\*.java`.
3. Execute `SupplyChainManager` : `java -cp .;lib\* edu.ucalgary.ensf409.SupplyChainManager`.
4. Upon execution, the username and password for the MySQL database will be prompted.

# How to run the tests
1. Using Command Line, navigate to the project folder containing edu and lib directories.
2. Compile the java files using the classpath: `javac -cp .;lib\* edu\ucalgary\ensf409\*.java`
3. Open the MySQL command line client and execute the command `source path\inventory.sql;` ,
where path is the pathname to the directory in which `inventory.sql` is located.
4. Run `SQLConnectorTest`: `java -cp .;lib\* org.junit.runner.JUnitCore edu.ucalgary.ensf409.SQLConnectorTest` and enter the database credentials.
5. Repeat step 3 to restore the database after using SQLConnectorTest.
6. Run `SupplyChainManagerTest`: `java -cp .;lib\* org.junit.runner.JUnitCore
edu.ucalgary.ensf409.SupplyChainManagerTest` and enter the database credentials.
7. Repeat step 3 to restore the database after using SupplyChainManagerTest.
8. Run `FurnitureTest`: `java -cp .;lib\* org.junit.runner.JUnitCore edu.ucalgary.ensf409.FurnitureTest`.
9. Run `InventoryTest`: `java -cp .;lib\* org.junit.runner.JUnitCore edu.ucalgary.ensf409.InventoryTest`.
10. Run `OrderFormTest`: `java -cp .;lib\* org.junit.runner.JUnitCore edu.ucalgary.ensf409.OrderFormTest`.

