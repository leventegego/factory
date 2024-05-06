
!start.

+!start <-
    ?pos(X, Y);
    +home(X, Y);
    !listen.

+!reset <-
    .abolish(client(C, X, Y));
    ?home(X, Y);
    !goto(X, Y);
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
    !goto(XC + 1, YC);
    .send(C, achieve, reset);
    !reset.
+!reject <-
    .print("rejected");
    !reset.



+!goto(X, Y): pos(X, Y).
+!goto(X, Y) <-
    move_towards(X, Y);
    .wait(300);
    !goto(X, Y).

