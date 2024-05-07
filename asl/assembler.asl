refill(reject).
counter(0).
!start.

+!start <-
    .random(C);
    !add(C * 50 + 50);

    .random(I);
    +interval(I * 1000 + 300);
    .my_name(A);

    ?pos(X, Y);
    .broadcast(tell, pos(A, X, Y));

    !loop.



+!loop <-
    !remove(1);
    !checkrefill;
    !checkoffer;
    !wait;
    !loop.

+!wait <-
    ?interval(I);
    .wait(I).

+!show: counter(C) & C <= 50 <-
    show(C);
    mark(1).
+!show <-
    ?counter(C);
    show(C);
    mark(0).


+!add(X) <-
    -+refill(reject);
    ?counter(C);
    -+counter(C + X);
    !show.
+!remove(X) : counter(C) & C > 0 <-
    ?counter(C);
    -+counter(C - X);
    !show.
+!remove(X) <-
    -+counter(0);
    !show.



+!checkrefill: refill(accept) | refill(inbound).
+!checkrefill: counter(C) & C <= 50 <-
    -+refill(accept);
    .my_name(A);
    .print("request");
    .broadcast(tell, request(A)).
+!checkrefill.

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
