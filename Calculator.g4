grammar Calculator;

expr: expr op=('*'|'/') expr # MulDiv
    | expr op=('+'|'-') expr # AddSub
    | INT                    # int
    | '('expr')'             # parens
    ;

INT: [0-9]+ ;
MUL: '*' ;
DIV: '/' ;
ADD: '+' ;
SUB: '-' ;
WS : [ \t\r\n]+ -> skip ;
