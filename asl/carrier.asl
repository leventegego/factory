
!start.

+!start <-
    ?pos(X, Y);
    +capacity(50);
    +home(X, Y);
    !listen.

+!reset <-
    ?home(X, Y);
    !goto(X, Y, break);
    !listen.

+!listen: request(A) <-
    -+client(A);
    .my_name(C);
    .send(A, tell, offer(C)).
+!listen <-
    .wait(500);
    !listen.


+!goto(X, Y, _): pos(X, Y).
+!goto(X, Y, break): request(_).
+!goto(X, Y, R) <-
    move_towards(X, Y);
    !goto(X, Y, R).


+!accept <-
    ?pos(p, XP, YP);
    !goto(XP, YP, ignore);
    ?capacity(C);
    show(C);
    ?client(A);
    ?pos(A, XA, YA);
    !goto(XA, YA + 1, ignore);
    .send(A, achieve, add(C));
    show(0);
    !reset.
+!reject <-
    !reset.





