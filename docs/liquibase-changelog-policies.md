## Liquibase changelog policy 

For now this document describes how to name changelog files and changeset IDs.

___

### Changelog files naming rule
The pattern for changelog file name is ```yyMMdd.taskID-branchname```.

Where: 
* ```yyMMdd``` date when you start (or end) to work on task. 
It is needed to preserve chronological order of files during sorting.
* ```taskID``` Zenhub ID of the task
* ```branchname``` the same text as the name of the task branch, 
just to have short description in history tables of liquibase(for example).

####  Example
```
220309.10-create_init_data_migration_set
```
### Changeset IDs naming rule
The pattern for changeset ID name is 
```taskID.<major-sequential-number|any-text-descriptor>.[minor-sequential-number]```

Where:
* ```taskID``` Zenhub ID of the task
* ```major-sequential-number``` unique ID in scope of current task (major ID) 
* ```text-descriptor``` short text descriptor unique in scope of current task (major ID)
* ```minor-sequential-number``` unique ID in scope of major ID (optional)

#### Example:
```
10.route.1
11.2.3
17.5
```

---
P.S. You may want to read 
[best practices article](https://www.liquibase.org/get-started/best-practices)