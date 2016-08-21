
# ANTLR4 calculator example

A short antlr4 tutorial with the calculator grammar and code examples for the following: 

- Java visitor 
- Java listener 
- JavaScript listener

## Set up env variables

    cat /etc/profile.d/antlr4.sh 
    export CLASSPATH=".:/usr/local/lib/antlr-4.5.3-complete.jar:$CLASSPATH"
    alias antlr4='java -jar /usr/local/lib/antlr-4.5.3-complete.jar'
    alias grun='java org.antlr.v4.gui.TestRig'

## Clean up

    cd antlr4calc
    rm Calculator*.js Calculator*.java *.tokens *.class

## Java Visitor

### Generate code

    antlr4 -no-listener -visitor Calculator.g4 

### Compile code

    javac Calculator*.java MainVisit.java

### Run it

    echo "((1+2) * 2 + 2)/( 2 )" | java MainVisit

## Java Listener

### Generate code

    antlr4 Calculator.g4 

### Compile code

    javac Calculator*.java MainListen.java

### Run it

    echo "((1+2) * 2 + 2)/( 2 )" | java MainListen

## JavaScript Listener

### Install antlr4 JavaScript runtime (requires nodejs and npm)

    npm install antlr4

### Generate code

    antlr4 -Dlanguage=JavaScript Calculator.g4

### Run it

    echo "((1+2) * 2 + 2)/( 1 + 1 )" | node Main.js


