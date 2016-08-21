
# ANTLR4 calculator examples

A short antlr4 tutorial with the calculator grammar and code examples for the following languages and design patterns:

- Java visitor
- Java listener
- JavaScript listener

which can be run independent of each other (see the corresponding sections). Credit goes to the author(s) of the framework antlr (http://www.antlr.org/) and the book "The Definitive ANTLR 4 Reference".

The code are tested in a docker container running centos 7, jdk 1.8, antlr4, and nodejs 4 (nodejs is only required for the JavaScript example). The docker image can be pulled from https://hub.docker.com/r/ouyi/docker-centos-dev/, which is however not a hard dependency of this tutorial, because the code examples shall work in any similar environment.

This repository can be cloned by doing:

    git clone https://github.com/ouyi/docker-centos-dev.git

Set up env variables by having these lines in the `/etc/profile.d/antlr4.sh` file (or in `~/.bashrc`)

    export CLASSPATH=".:/usr/local/lib/antlr-4.5.3-complete.jar:$CLASSPATH"
    alias antlr4='java -jar /usr/local/lib/antlr-4.5.3-complete.jar'
    alias grun='java org.antlr.v4.gui.TestRig'

Clean up

    cd antlr4calc
    rm Calculator*.js Calculator*.java *.tokens *.class

## Java Visitor

Generate code

    antlr4 -no-listener -visitor Calculator.g4

Compile code

    javac Calculator*.java MainVisit.java

Run it

    echo "((1+2) * 2 + 2)/( 1 + 1 )" | java MainVisit

## Java Listener

Generate code

    antlr4 Calculator.g4

Compile code

    javac Calculator*.java MainListen.java

Run it

    echo "((1+2) * 2 + 2)/( 1 + 1 )" | java MainListen

## JavaScript Listener

Install antlr4 JavaScript runtime (requires nodejs and npm)

    npm install antlr4

Generate code

    antlr4 -Dlanguage=JavaScript Calculator.g4

Run it

    echo "((1+2) * 2 + 2)/( 1 + 1 )" | node Main.js

