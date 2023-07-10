# Random Sentence Generator

## Description

The “Random Sentence Generator” is a piece of technology to create random sentences from a pattern known as a grammar. A grammar is a template that describes the various combinations of words that can be used to form valid sentences.

Specify a pattern consisting of normal text and "non-terminal" tags in brackets (<>). A non-terminal is a placeholder that will expand to another sequence of words when generating a poem. In contrast, a "terminal" is a normal word that is not changed to anything else when expanding the grammar.

Once the program reads in the grammar, it will be able to produce random expansions from it. It begins with a single non-terminal with the “Start” label. For a non-terminal, consider its definition, which will contain a set of productions. Choose one of the productions at random. Take the words from the chosen production in sequence, (recursively) expanding any which are themselves nonterminals as it goes.

## Usage

Directly run in Main.java when grammar files are all put in grammar directory. Or run in gradle test or junit files.

When the program starts, a directory name will be provided as an argument. This directory will include several grammar files.

The program will allow the user to choose one of these grammars. When a grammar is chosen, a generated sentence will be displayed. Ask the user if they would like another one, and continue generating and printing sentences until the user says "n".

Then, go back to the main menu, and allow the user to choose another grammar if they would like. If user says "q", exit the program.

```
The following grammars are available:
1. Insults
2. Term Paper Generator
3. Dear John letter
Which would you like to use? (q to quit)
1
With the fury of Thor’s belch, may the hosts of Hades find your
earlobes suddenly delectable.
Would you like another? (y/n)
y
You mutilated goat.
Would you like another? (y/n)
n
The following grammars are available:
1. Insults
2. Term Paper Generator
3. Dear John letter
Which would you like to use? (q to quit)
q
```

## Build 

In The JsonFileReader class, readJsonFile function reads the specific Grammar .json file into a HashMap with key String and value ArrayList of String info, which contains the file contents

The JsonGrammar class assigns the Grammar Title and Description fields and stores the Json Info into a HashMap with key String and value ArrayList of String info, which contains the file contents, that we can find and replace the contents through the key labels.

For the SentenceGenerator class, This class takes in a Grammar object (mentioned above), and randomly builds a complete string sentence through recursive string replacement where it finds placeholders, denoted by specific brackets. The replaceAtrributeValue function replaces the placeholder with the value of the attribute in the JSON data recursively. The generateRandomSentence function check whether JSON data with the key attribute exists and replace the placeholder with the key value of the attribute recursively.

UserInterface class serves as a front-end interface for a user. It creates the Grammar objects, and then from their grammarTitles creates/prints a menu. The class then takes in input from the user to allow them to select a grammar and then generates and prints it.