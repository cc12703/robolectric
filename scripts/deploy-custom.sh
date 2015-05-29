#!/bin/bash
#
# Deploy a custom build to youtou.
#

set -e

PROJECT=$(cd $(dirname "$0")/..; pwd)

echo "Building Robolectric..."
cd "$PROJECT"; mvn -D skipTests -D repositoryId=youtou-repo-snapshots clean deploy 

echo "Building shadows for API 16..."
cd "$PROJECT"/robolectric-shadows/shadows-core; mvn -P android-16 -D skipTests -D repositoryId=youtou-repo-snapshots clean deploy

echo "Building shadows for API 17..."
cd "$PROJECT"/robolectric-shadows/shadows-core; mvn -P android-17 -D skipTests -D repositoryId=youtou-repo-snapshots clean deploy

echo "Building shadows for API 18..."
cd "$PROJECT"/robolectric-shadows/shadows-core; mvn -P android-18 -D skipTests -D repositoryId=youtou-repo-snapshots clean deploy

echo "Building shadows for API 19..."
cd "$PROJECT"/robolectric-shadows/shadows-core; mvn -P android-19 -D skipTests -D repositoryId=youtou-repo-snapshots clean deploy

echo "Building shadows for API 21..."
cd "$PROJECT"/robolectric-shadows/shadows-core; mvn -P android-21 -D skipTests -D repositoryId=youtou-repo-snapshots clean deploy
