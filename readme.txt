KNZ Hotel Booking App (Group Project)

--Important--
Once the folder is unzip launch this folder on the IDE

Application is launch in your IDE with Java version 19
Launch this application from the Main.java class
Application requires all its components and local dependencies within knz-hotel-booking-app folder. 
Do not move any parts of this application outside or any new directory from this folder

If there is any issues regarding the third party dependencies from Apache Maven
type in console:
mvn clean install

--Alternative:(Temporary Solution)--
If you want to bypass tests temporarily during development:

bash and run code below

mvn clean package -DskipTests

After Fixing, Build:
bash and run code below

mvn clean package
java -jar target/knz-hotel-booking-app-1.0-SNAPSHOT-jar-with-dependencies.jar

This application does not support GUI components rescaling. 
Ensure that your computer display supports at least (1920 x 1080) resolution and is on native 100% scaling on Windows. 
Thus, the application will display everything with correct proportion.
