refill(reject).
!start.

+!start <-
    .random(C);
    +counter(C * 50 + 50);
    !show;

    .random(I);
    +interval(I * 1000 + 200);
    .my_name(A);

    ?pos(X, Y);
    .broadcast(tell, pos(A, X, Y));

    !loop.



+!loop <-
    !remove(1);
    !show;
    !checkrefill;
    !checkoffer;
    !wait;
    !loop.

+!wait <-
    ?interval(I);
    .wait(I).



+!add(X) <-
    ?counter(C);
    -+counter(C + X);
    -+refill(reject).
+!remove(X) : not counter(0) <-
    ?counter(C);
    -+counter(C - X).
+!remove(X) <-
    -+counter(0).
+!show <-
    ?counter(C);
    show(C).



+!checkrefill: refill(accept) | refill(inbound).
+!checkrefill: counter(C) & C <= 50 <-
    mark(1);
    -+refill(accept);
    .my_name(A);
    .print("request");
    .broadcast(tell, request(A)).
+!checkrefill <-
    mark(0).


+!checkoffer: offer(C) & refill(accept) <-
    .print("accept");
    -+refill(inbound);
    .my_name(A);
    .broadcast(untell, request(A));
    .send(C, achieve, accept);
    .abolish(offer(C));
    !checkoffer.
+!checkoffer: offer(C) <-
    .print("reject");
    .send(C, achieve, reject);
    .abolish(offer(C));
    !checkoffer.
+!checkoffer.

+offer(_): refill(accept).
+offer(C) <-
    .print("auto-reject");
    .send(C, achieve, reject);
    .abolish(offer(C)).
