# Github Workflow

## Overall Structure

There will be a **singular** GitHub repository for the entire team, called 2020-Infinite-Recharge. The repository will be organized with the following branches:

- master
- end_game
- integrated_robot (or whatever we are renaming this to)
- control_wheel
- autonomous
- ball_scorer

## Software Students

Each software team member will have a fork of this main repository on their GitHub accounts. 

```
You will first need to login to Github.com using your personal Github account. Your fork will be associated with your account.
Navigate to https://github.com/frc-team-3341/frc-3341-InfiniteRecharge
Select the 'Fork' button on the upper right of the page. This will create the fork and redirect to the site for your fork, with a URL containing your account info.
```

Operating in this forked repository, students will work within the branch they are assigned to.

```
From the repo site for your fork, copy the URL to clone your fork by selecting the 'Clone or download' button.
Clone your forked repo from the CLI or GithubDesktop. The CLI command is: **git clone <repo URL>**. For example: git clone https://github.com/alanfhoss/frc-3341-InfiniteRecharge.git. From Github Desktop, you can clone the fork from the menu option "File -> Clone Repository...".
Add a remote upstream for your fork that represents the original repo: **git remote add upstream https://github.com/frc-team-3341/frc-3341-InfiniteRecharge.git**
Checkout the branch for your project. From the CLI: **git checkout <project branch>**. For example: **git checkout control_wheel**. From the Github Desktop repository view, you can choose the branch from the drop-down list within the "Current Branch" selection across the top of the screen.
```

The students will regularly update their forks with the code from the main GitHub repository. They will regularly update their own repositories, even if they have not completed a feature or something hasn’t been tested.

```
To refresh your fork from the remote upstream repo: **git fetch upstream**. On Github Desktop, select the 'Fetch Origin' button.
Checkout your branch, if not already checked out: **git checkout <branch>**
Merge any changes from the upstream branch with your local (forked) branch: **git merge upstream/<branch>**. From Github Desktop, you can select the 'Current Branch' button and select the 'Choose a branch to merge into <your branch>'. You should select the 'upstream/<your branch>' branch for this operation. You may at this point need to resolve any merge conflicts that result (which is why you should frequently refresh your fork AND commit your changes to your fork frequently!).

To add any changed files via the CLI: **git add <files>** OR add all changes via **git add -A**. If using Github Desktop, you will note now that modified files are showing up in the 'Changes' view. Ensure that the list is correct, and uncheck any files that you don't want to commit as part of this change.
Commit your changes locally, again from the CLI: **git commit -m "A reasonable commit message that is descriptive"**. From Github Desktop, you can specify a commit message and description in the lower left of the 'Changes' view.
Before pushing, you may want to refresh your local fork as noted above.

To push to the remote for your fork: **git push** or select the 'Push' option on the Github Desktop main view following the commit.
```

When students have completed and tested a feature, they will make a pull request to their assigned branch in the master GitHub project.

```
It is possible to either create a Pull Request from either Github Desktop main view OR the Github page for your forked repo from the 'Pull Requests' tab. When creating a pull request, IT'S IMPORTANT TO MAKE SURE THAT YOUR BRANCH IS SELECTED AS THE REQUESTED BRANCH FOR BOTH YOUR FORK AND THE UPSTREAM (WESTVIEW) REPO.
```

## Software Leads

There will be a primary software lead assigned to each branch. This lead has the permissions to approve pull requests to  branches on the frc-team-3341 GitHub project. Software leads, when they are coding, will follow the same workflow as the **Software Students**.  The difference is the additional responsibility of reviewing and eventually accepting pull requests to the main branch in the frc-team-3341 organization project. These software leads will also, once their branch has been tested, be responsible for making pull requests to the master branch.

## Software Managers

These are the VP of Software, President, and Mentors. The managers will review/approve pull requests from the subsystem branches to the master branch. The master branch will be where the most recent stable robot code is located. Even if it doesn’t have all of the features, it’s guaranteed to work at any given point of time.
