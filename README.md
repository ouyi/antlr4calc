
# ANTLR4 calculator example

## Set up env variables

    cat /etc/profile.d/antlr4.sh 
    export CLASSPATH=".:/usr/local/lib/antlr-4.5.3-complete.jar:$CLASSPATH"
    alias antlr4='java -jar /usr/local/lib/antlr-4.5.3-complete.jar'
    alias grun='java org.antlr.v4.gui.TestRig'

## Generate code

    antlr4 -no-listener -visitor Calculator.g4 

## Compile code

    javac *.java

## Run it

    echo "((1+2) * 2)/( 3)" | java Main
