// Agent alice in project factory

/* Initial beliefs and rules */

/* Initial goals */

!start.

/* Plans */

+!start <-
    !pos(2,2);
    !pos(6,9);
    !start.



+!pos(X,Y) : pos(X,Y).
+!pos(X,Y) <-
    move_towards(X,Y);
    !pos(X,Y).

