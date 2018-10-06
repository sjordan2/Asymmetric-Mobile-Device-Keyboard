# Project Title

Sean Jordan's implementation of the Mobile Device Keyboard, one of the programming challenges for Asymmetrik, LTD.

## Getting Started

How to run the Mobile Device Keyboard application.

### Prerequisites

Before you can run this application, you need to install the following prerequisites.

```
Java JDK 8 or higher
Apache Maven
```

### Installing

Here are instructions on how to install the Mobile Device Keyboard application on your system.

First, clone the repositiory onto your computer by running the following command in your git command line:

```
git clone https://github.com/sjordan2/Asymmetric-Mobile-Device-Keyboard.git
```

Then, in the folder where the repository was downloaded, run the following command in your command line:

```
mvn clean install -Dmaven.wagon.http.ssl.allowall=true
```

This concludes the installment of the Mobile Device Keyboard Application.

## Running the application

In the project folder, there should now be a /target directory created during the maven install process. Inside, you should find multiple files and directories. The important one is asymmetrik-mobile-keyboard-1.0.jar. This contains the implementation code as well as the Apache Commons Language Library.

### Executing the JAR file

In order to run the application, run the following command from the /target folder (or whereever you moved the JAR file):

```
java -jar asymmetrik-mobile-keyboard-1.0.jar
```

### Navigating the application

Upon running the application, a GUI window should open. You will see a lot of text fields and buttons.

### Training the algorithm

To start training the algorithm, input a sentence to the text field next to the "Train" button. Once you are finished, click on the "Train" button. This sentence is chopped up into words, and each word is converted to a Candidate object.

### Testing the algorithm

To test the effectiveness of the algorithm, start typing a word into the text field within the "Test" box. You should see recommendations of words appear in the three buttons below where you are typing. If that is the word you want, click that button. If the word you are typing isn't in memory, then you will see your text input appear on one of the buttons. Click that to register that word as a candidate in memory.

### Miscellaneous Instructions

At any time, you can see the list of current candidates, their words, and their confidences by clicking the "Show Current Candidates" button at the top right of the window. This will bring up a dialog window that shows all candidates and their respective properties.

## Built With

* [Eclipse](https://www.eclipse.org/) - The IDE used for developing this application
* [Maven](https://maven.apache.org/) - Dependency Management (Apache Commons-Lang3)

## Authors

* **Sean Jordan** - [sjordan2](https://github.com/sjordan2)

## Acknowledgments

* The Apache Foundation
* Asymmetrik, LTD
* My dog
