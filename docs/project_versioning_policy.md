## Project versioning policy

This document describes guide of GitFlow process established on this project.
Whenever you want to participate to develop some functionality or contribute
some changes to the current project, please follow rules described in this
file.

___

### Git Flow

The GitFlow schema approved for this project is illustrated by the following
sketch.

![Git Flow Scheme](media/git_flow_scheme.png)

* ```master``` branch: Contains stable, released code. Any direct pushes from
working (feature, dev) branches to this branch are NOT ALLOWED. All pull
requests will be rejected.

* ```release``` branch: Temporary branch. Contains all workable,
requirement-matched code for future deploy. Support for preparing a new product
release. Allows you to fix small bugs and prepare different metadata for the
release.

* ```dev``` branch: Develop branch should be inherited from latest master
version. Developers should to create a separate ```feature``` branches for
implement new functionality and open a pull request to push back a workable
code into this branch. Also, this branch allow strict merge of minor
corrections (e.g. typos, renaming, etc.)

* ```feature``` branch: Should be created for implementation a new
functionality. The development of new features starts from the "dev" branch.
Once, when all work is done and matches all requirements this branch should
be merged into ```dev``` branch. This branch is temporary. Thus, after the pull
request is closed, this branch will be deleted. Naming of this branch are
numbered by tracking system (e.g. ZenHub) and should implement all
functionality described in the ticket.

* ```hotfix``` branch: Branch created for immediate fix functionality of
released code if it goes wrong. The only branch which can be merged
directly to the master branch.

* ```bugfix``` branch: Branch for fixes of non-critical errors in the app 
behaviour. This branch should be inherited from dev, opened after
```bug report``` ticket were opened and should be numbered by ticket's number.
The correction which presented in this code should be covered by a test case
that catches the bug described in the ZenHub ticket. After all work is done
all commits should be merged in the ```dev``` branch. Naming in this branch
is equivalent to ```hotfix``` branch naming.

* ```refactor``` branch: This branch should be created for the correction
of the implementation which is working correctly but the way of its
implementation has some flaws which are conflict with best practices,
have some security vulnerabilities, or violates
[Java Code Convention](https://www.oracle.com/technetwork/java/codeconventions-150003.pdf).
This branch should be opened in according to Zenhub ticket. Result committed in
this branch should be merged into dev by opening a pull request.

### Examples of naming:

```
GOOD:
feature/#2-Creating_new_functionality
hotfix/#245-Unexeptable_response_during_pay_process
bugfix/#252-Incorrect_route_building
refactor/#632-Ticket_booking_service_logic_refactor

BAD:
Feature/230-WrongBranchName
Hotfix/#134-Fix-branch-naming
```
