
# ANTLR4 calculator example

A short tutorial with the calculator grammar and code for both the visitor and the listener patterns.

## Set up env variables

    cat /etc/profile.d/antlr4.sh 
    export CLASSPATH=".:/usr/local/lib/antlr-4.5.3-complete.jar:$CLASSPATH"
    alias antlr4='java -jar /usr/local/lib/antlr-4.5.3-complete.jar'
    alias grun='java org.antlr.v4.gui.TestRig'

## Clean up

    cd antlr4calc
    rm Calculator*.java *.tokens *.class  

## Visitor

### Generate code

    antlr4 -no-listener -visitor Calculator.g4 

### Compile code

    javac Calculator*.java MainVisit.java

### Run it

    echo "((1+2) * 2 + 2)/( 2 )" | java MainVisit

## Listener

### Generate code

    antlr4 Calculator.g4 

### Compile code

    javac Calculator*.java MainListen.java

### Run it

    echo "((1+2) * 2 + 2)/( 2 )" | java MainListen
