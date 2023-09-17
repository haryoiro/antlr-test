grammar Calc;

@header {
package com.haryoiro.calcformat.antlr;
}


start: expr EOF;

expr: add_expr;

add_expr
    : mul_expr ( PLUS_MINUS mul_expr )*
    ;

mul_expr
    : atom ( MUL_DIV atom )*
    ;

atom
    : NUMBER
    | IDENTIFIER
    | LPAREN expr RPAREN
    ;

PLUS_MINUS: '+' | '-';
MUL_DIV: '*' | '/';
LPAREN: '(';
RPAREN: ')';

NUMBER: [0-9]+;
IDENTIFIER: [a-zA-Z]+;

WS: [ \t\r\n]+ -> skip;