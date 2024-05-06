
!start.

+!start <-
    ?pos(X, Y);
    +capacity(50);
    +home(X, Y);
    +broken(false);
    !listen.

+!reset <-
    .print("reset");
    .abolish(client(A));
    -+broken(false);
    mark(0);
    ?home(X, Y);
    !goto(X, Y, break);
    !listen.

+!listen: broken(true) <-
    !maintenance.
+!listen: request(A) <-
    -+client(A);
    .print("offer");
    .my_name(C);
    .send(A, tell, offer(C)).
+!listen <-
    .wait(500);
    !listen.

+!accept <-
    ?pos(p, XC, YC);
    ?client(A);
    ?pos(A, XA, YA);

    !goto(XC, YC, ignore);
    !load;
    !goto(XA, YA + 1, ignore);
    !finish.
+!reject <-
    .print("rejected");
    !reset.

+!load: broken(true).
+!load <-
    ?capacity(C);
    show(C).

+!finish: broken(true) <-
    ?client(A);
    .send(A, achieve, add(0));
    show(0);
    !maintenance.
+!finish <-
    ?client(A);
    ?capacity(C);
    .send(A, achieve, add(C));
    show(0);
    !reset.

+!goto(X, Y, _): pos(X, Y) | broken(true).
+!goto(_, _, break): request(_).
+!goto(X, Y, F): worker(close) <-
    .wait(300);
    !goto(X, Y, F).
+!goto(_, _, _): .random(R) & R < 0.01 <-
    -+broken(true);
    mark(1).
+!goto(X, Y, F) <-
    move_towards(X, Y);
    .wait(300);
    !goto(X, Y, F).






+!maintenance <-
    .my_name(C);
    ?pos(X, Y);
    .broadcast(tell, request(C, X, Y));
    !checkoffer.



+!checkoffer: offer(W) <-
    .print("accept");
    .my_name(C);
    ?pos(X, Y);
    .broadcast(untell, request(C, X, Y));
    .send(W, achieve, accept);
    .abolish(offer(W));
    !reject_offers.
+!checkoffer <-
    .wait(300);
    !checkoffer.
+!reject_offers: offer(W) <-
    .print("reject");
    .send(W, achieve, reject);
    .abolish(offer(W));
    !reject_offers.
+!reject_offers.

+offer(_): broken(true).
+offer(W) <-
    .print("auto-reject");
    .send(W, achieve, reject);
    .abolish(offer(W)).
