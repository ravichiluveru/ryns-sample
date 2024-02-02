#!/bin/bash
. ./scripts/include.sh

# Below to disable test while working experiments to save time on builds.
#./gradlew clean build shadowJar -x test -PdoRelease=true "$@"
./gradlew clean build shadowJar -PdoRelease=true "$@"
# Use below command for local maven repo publish
# ./gradlew clean build publishToMavenLocal -PdoRelease=true