"use strict";

var antlr4 = require('antlr4/index.js');
var CalculatorLexer = require("./CalculatorLexer.js");
var CalculatorParser = require("./CalculatorParser.js");
var CalculatorListener = require("./CalculatorListener.js");

// use create for inheritance, see https://developer.mozilla.org/en/docs/Web/JavaScript/Inheritance_and_the_prototype_chain
var Listener = function() {
    CalculatorListener.CalculatorListener.call(this); // chain the constructor
};
// chaining the prototypes
Listener.prototype = Object.create(CalculatorListener.CalculatorListener.prototype);

// override default listener behavior
Listener.prototype.stack = [];
Listener.prototype.exitInt = function(ctx) {      
    // stack stores integers only
    this.stack.push(parseInt(ctx.INT().getText()));
}; 
Listener.prototype.exitMulDiv = function(ctx) {
    var right = this.stack.pop();
    var left = this.stack.pop();
    var result;
    if (ctx.op.type == CalculatorParser.CalculatorParser.MUL) {
        result = left * right;
    } else {
        result = left / right;
    }
    this.stack.push(result);
};
Listener.prototype.exitAddSub = function(ctx) {
    var right = this.stack.pop();
    var left = this.stack.pop();
    var result;
    if (ctx.op.type == CalculatorParser.CalculatorParser.ADD) {
        result = left + right;
    } else {
        result = left - right;
    }
    this.stack.push(result);
};

var main = function(input) {
    var chars = new antlr4.InputStream(input);
    var lexer = new CalculatorLexer.CalculatorLexer(chars);
    var tokens  = new antlr4.CommonTokenStream(lexer);
    var parser = new CalculatorParser.CalculatorParser(tokens);
    parser.buildParseTrees = true;
    var tree = parser.expr();

    var printer = new Listener();
    antlr4.tree.ParseTreeWalker.DEFAULT.walk(printer, tree);
    console.log(printer.stack.slice(-1).pop());
}

process.stdin.setEncoding('utf8');
var s = '';
process.stdin.on('readable', () => {
    var chunk = process.stdin.read();
    if (chunk !== null) {
        s += chunk;
    }
});
process.stdin.on('end', () => {
    main(s);
});

