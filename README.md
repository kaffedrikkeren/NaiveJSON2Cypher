# NaiveJSON2Cypther
Naive JSON to Cypher converter solving one particular conversion task.

Usage — something like:
`lein run <file_name.json > cypher.txt`

Alternatively, one can build an independent JAR:

`lein uberjar`

And then run it:

`java -jar NaiveConverter-0.0.1-standalone.jar test.json`

Converter expects a file where each line is a JSON array:
```
[{"type" : "Type1", "uid" : "0982345972394587432"}, {"type" : "Type2", "uid" : "234958349589"}]
[{"type" : "Type1", "uid" : "0918340958345797832"}, {"type" : "Type2", "uid" : "234349509788"}]
...
```

And converts it into a Cypher query creating two nodes and a relationship between them to be used on Neo4j. 
```
CREATE (n:Type1 {uid: "0982345972394587432");

CREATE (n:Type2 {uid: "234958349589");

MATCH (from:Type1 {uid: "0982345972394587432"}), (to:Type2 {uid: "234958349589"})
CREATE (from)-[r:BELONGS_TO]->(to);

CREATE (n:Type1 {uid: "0918340958345797832");

CREATE (n:Type2 {uid: "234349509788");

MATCH (from:Type1 {uid: "0918340958345797832"}), (to:Type2 {uid: "234349509788"})
CREATE (from)-[r:BELONGS_TO]->(to);
```
