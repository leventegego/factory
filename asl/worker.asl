
d2(X1, Y1, X2, Y2, D) :-
    ((X2 - X1) * (X2 - X1)) + ((Y2 - Y1) * (Y2 - Y1)) = D.

!start.

+!start <-
    ?pos(X, Y);
    +home(X, Y);
    !listen.

+!reset <-
    .abolish(client(C, X, Y));
    ?home(X, Y);
    !goto(X, Y, 0);
    !listen.


+!listen: request(C, X, Y) <-
    .print("offer");
    -+client(C, X, Y);
    .my_name(W);
    .send(C, tell, offer(W)).
+!listen <-
    .wait(500);
    !listen.

+!accept <-
    .print("accepted");
    ?pos(p, XW, YW);
    ?client(C, XC, YC);
    !goto(XC, YC, 1);
    .send(C, achieve, reset);
    !reset.
+!reject <-
    .print("rejected");
    !reset.



+!goto(X, Y, D): pos(XP, YP) & d2(X, Y, XP, YP, DP) & DP <= D.
+!goto(X, Y, D) <-
    move_towards(X, Y);
    .wait(300);
    !goto(X, Y, D).

