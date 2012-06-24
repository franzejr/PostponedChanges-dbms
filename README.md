#Postponed Recovery

The algorithm is a implementation from the ARIES protocol. We can recover only committed transactions.

###Analyze

Check all the log looking for transactions that has already been committed.\n2

###Redo
This is the main point. The algorithm output must be this redo list.

###Undo

We dont't have a undo list, cause We don't have a undo List.

#Input

Log:
[0|0|T1|w|x|10|20]
[1|1|T2|w|y|30|40]
[2|2|T2|w|x|70|80]
[3|3|T1|w|z|100|200] 
[4|4|T3|w|x|100|200]
[5|5|T1|w|o|110|220]
[6|6|T3|w|z|04|20]
[7|7|T1|c|_|_|_]
[8|8|T3|c|_|_|_]
[8|8|T2|c|_|_|_]

#Output

DIRTY PAGE: 

{o=5, z=3, y=1, x=0}

TRANSACTION PAGE: 

{T1=7, T3=8, T2=8}


REDO-LIST:

LSN: 0 | Transaction Timestamp: 0 | Transaction ID: T1 | Operation Type: w | Operation Target Object: x | Before Object Image: 10 | After Object Image: 20
LSN: 1 | Transaction Timestamp: 1 | Transaction ID: T2 | Operation Type: w | Operation Target Object: y | Before Object Image: 30 | After Object Image: 40
LSN: 2 | Transaction Timestamp: 2 | Transaction ID: T2 | Operation Type: w | Operation Target Object: x | Before Object Image: 70 | After Object Image: 80
LSN: 3 | Transaction Timestamp: 3 | Transaction ID: T1 | Operation Type: w | Operation Target Object: z | Before Object Image: 100 | After Object Image: 200
LSN: 4 | Transaction Timestamp: 4 | Transaction ID: T3 | Operation Type: w | Operation Target Object: x | Before Object Image: 100 | After Object Image: 200
LSN: 5 | Transaction Timestamp: 5 | Transaction ID: T1 | Operation Type: w | Operation Target Object: o | Before Object Image: 110 | After Object Image: 220
LSN: 6 | Transaction Timestamp: 6 | Transaction ID: T3 | Operation Type: w | Operation Target Object: z | Before Object Image: 4 | After Object Image: 20

Final States:

o = 220
z = 20
y = 40
x = 200