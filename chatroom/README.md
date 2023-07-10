# Group_fighting_zijian
CS5010 repo for Fall2022; Northeastern Seattle.

## Introduction

The server will be responsible for managing the client connections (up to 10 clients can be connected and in a chat room at one time), accepting messages from one client and sending the messages to all attached clients. Clients will be able to either send a message that is public in the chat room, or that goes directly to a single, specified client.

## About Jar file

The project 4 was contained as a jar file for generating insult message. The file is listed as
`assignment6/assignment4-1.0-SNAPSHOT.jar`

## Assumptions

In the client:
1. if there are multiple commands (for @user,!user and @all), only the command at the
   top of the input will be use.
2. if the user's input does not match any command, the program will treat it as "@all", such as "who are you", "! hello", " @ home" and so on
3. if user input empty string, the client interface will ask for re-input again
4. @all including sending message to the client itself.
5. if CONNECT_RESPONSE returns false, the client won't be able to log off. Only it return true, then
   the program can be terminated.
6. Client will try to reconnect server when server closed during the phase of login. After login,
   if the server closed, only error message will be thrown and reconnection will not happen
7. Server can connect up to 10 clients

## Commands

- logoff: sends a DISCONNECT_MESSAGE to the server

- who: sends a QUERY_CONNECTED_USERS to the server

- @user: sends a DIRECT_MESSAGE to the specified user to the server

- @all: sends a BROADCAST_MESSAGE to the server, to be sent to all users connected

- !user: sends a SEND_INSULT message to the server, to be sent to the specified user



Note:

1. Find Run Section at the Tool Bar section and Click Run to start Server...

2. Find the main function in the

3. Click Run configuration

    * Add the hostname and port number of the server to the CLI arguments (ex: localhost 6666). Note that the port number for server is fixed to 6666 in this program.
    * In the modify options, make sure you select the "allow multiple instances" to let you run multiple client program in the IDE.

4. Find Run Section at the Tool Bar section and Click Run to start Client. You can run multiple clients at this step but make sure wait for some time after starting the server.

5. There is some example how you would interact with our program

```console
please Type userName
bob
There are 0 other connected clients

please enter your command (use "?" to get all valid commands)

?
• logoff: sends a DISCONNECT_MESSAGE to the server
• who: sends a QUERY_CONNECTED_USERS to the server
• @user: sends a DIRECT_MESSAGE to the specified user to the server
• @all: sends a BROADCAST_MESSAGE to the server, to be sent to all users connected
• !user: sends a SEND_INSULT message to the server, to be sent to the specified user
please enter your command (use "?" to get all valid commands)

!user
please enter your command (use "?" to get all valid commands)
cannot find receiver, please check the username

!alice
please enter your command (use "?" to get all valid commands)
"You contaminated mountain of fungus." is sent to user alice.
 please don't send insult message again, thank you!

@alice hello
please enter your command (use "?" to get all valid commands)
"hello" is sent to user alice

@all hello
please enter your command (use "?" to get all valid commands)
"hello" sent to all connected clients

who
please enter your command (use "?" to get all valid commands)
query

========online clients======
client ： bob
client ： alice

logoff
You are no longer connected
```

> Include the entry point to your program.
Include how to run your server and client.
<pre>
To run the program, you should first run the server which is the ServerStarter in the server package.
Then, start ClientStarter in the client package. Each time you start a client it will need you to enter the name.
After entering it, it will automatically try to connect to the server end.
If it succeeds, client will receive message noticing him that he logged in successfully,
otherwise will receive message about the wrong reason. And if you want to start other clients,
just run the ClientStarter class again. Then you can type the command to interact to others in console. 
</pre>


> Give a high-level description of key classes/methods.

<pre>
We include classes that relate to client in the client package and classes that relate to server
in the server package and classes that relate to protocol in the constants package.
In the util package, there are two interfaces that are about data stream.
</pre>

>Include any assumptions you made about the nature of the problem.
<pre>
To enable up to 10 clients to connect to the server, 
we need to use multi-thread, when the client first connects successfully, 
we will generate a new thread about the server and store it in a map according to client’s name.
We need the map to store all the thread because when we need to send message to 
others we will need the map to find the corresponding client’s socket. 
Also, we will need to generate a new thread about the client, because different clients should have different socket.
</pre>


>Include steps you took to ensure correctness.
<pre>
When a new client wants to connect to the server, 
we will first judge whether there are already 10 online clients. 
if it is, we will not allow that user to connect. Also, it the name already exists,
we will not permit its connection. When a client wants to send message to others, 
we will judge if the receiver exists. If not, we will not permit it. When a user wants logoff, 
we will delete its record in map.
</pre>