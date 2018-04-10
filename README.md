maprfs-example
===

This sample project demonstrates basic MapR-FS Read/Write operations.
There are two possible ways to access files on the maprfs filesystem on a cluster deployed in my network, from a remote machine that is not part of the cluster
1. NFS
2. MapRFS Client (Demonstrated in this example)


_1) NFS_
===

To access the filesystem using NFS, please follow the document - "Accessing Data with NFS" on our documentation portal. This method allows the use of any generic Linux application to access files on the cluster as though it were on the local filesystem.

_2) MaprFS client._
===

MaprFS  also allows access to the cluster using the standard "hadoop fs <arguments>" commands the same way as used in HDFS. The MapR FS client also allows you to use the Hadoop API in a fully compatible way.
The client needs to be configured to access the maprfs cluster as described in this document  "Setting Up the Client". Once done, you can use a URL of the form maprfs:///path/of/file to access the cluster filesystem. It is also possible to use a URL of the form hdfs:///path/of/file since, by default, maprfs is configured as the implementation for hdfs.

Usage
===

Clone this project, then:

```
mvn package
java -cp $(hadoop classpath):target/maprfs-example-6.0.0.jar MapRTest hdfs://tmp/ /tmp
```

You should see output like:

```
mkdir( /tmp/dir) went ok, now writing file
write( /tmp/file.w) went ok
reading file: /tmp/file.w
Read ok
```
