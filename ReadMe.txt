To start playing our server-based checkers game, download the code from the github repository and add the code to a compiler of choice. After getting the code added, we can then start with running the program. 

Before getting started with our checkers game, the server needs to be started either on your computer or someone else's. If the server will be ran on your computer, start with step 1, if not, skip the starting server step

Starting server (optional)
1. Locate the "batFiles" folder in the project folders and open it. 
2. Look for the "gameServer.bat" file and run it. The Server GUI will automatically open a prepopulated host and port.
3. Click listen on the Server GUI. At this point, the server is started and is just waiting for the clients to connect to it. 

Starting the client GUI (local computer)
1. Locate the batFiles folder in the project folders and open it. 
2. Look for the gameClient.bat file and run it. The Client GUI will automatically open. If the host and port were not messed with, the Client will automatically connect with the server. 
3. After the Client GUI connects to the server, you have the option to login or create an account. Once the login or create account information are validated, the GUI will display a waiting screen, here, the server is waiting for another player to point. Steps 1-3 can be repeated to have both clients play on the same computer. Otherwise, follow the Starting the client GUI (other computer)

Starting the Client GUI (connecting to another computer)
1. Locate the batFiles folder again and open it. 
2. Look for the gameClient.bat and right-click on the file and find the edit option. 
3. Once the gameClient.bat is opened, replace "localhost" with the IPV4 address of the computer you wish to connect to, and replace "8300" with the port of the server gui from the other computer.
4. Save the gameClient.bat file and close the file text editor. 
5. Start the bat file again, the client GUI will open and should automatically establish a connection with the other computer.

Once 2 players are connected to the server, the game of checkers will start.  