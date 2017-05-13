#!/bin/bash

while [[ $# -gt 1 ]]
do
key="$1"

case $key in
    -u|--username)
    USER="$2"
    USERNAME="${USER%%@*}"
    shift # past argument
    ;;
    -r|--repo)
    REPO="$2"
    shift # past argument
    ;;
    -g|--gitrepo)
    GIT_REPO="$2"
    shift # past argument
    ;;
    --default)
    DEFAULT=YES
    ;;
    *)
    ;;
esac
shift # past argument or value
done

printf "Pull Repo from: $GIT_REPO \n"

$(git clone "$GIT_REPO")

GIT=$(basename "${GIT_REPO%%.git*}")
$(mv "$GIT" "$REPO")
GIT_DIR=$(PWD)
GIT_DIR=$GIT_DIR/$REPO

printf "\nCreating New Bitbucket Repository: $REPO \n"
$(sleep 0.5)

RESPONSE=$(curl -X POST -u "$USER" -H "Content-Type: application/json" \
                               https://api.bitbucket.org/2.0/repositories/"$USERNAME"/"$REPO" \
                               -d '{ "scm": "git", "is_private": "true", "fork_policy": "no_public_forks" }')
$(sleep 0.5)
printf "\nMirror Push to Bitbucket"
$(git -C "$GIT_DIR" push --mirror git@bitbucket.org:"$USERNAME"/"$REPO".git)

MASTER=https://"$USERNAME"@bitbucket.org/"$USERNAME"/"$REPO".git
printf "\n Setting new origin for local Repository: "$REPO"..."
$(git -C "$GIT_DIR" remote set-url origin "$MASTER")

printf "\nChecking origin of local Repository: "$REPO". \n"
REMOTE_SET=$(git -C "$GIT_DIR" remote -v)
printf "$REMOTE_SET \n"
