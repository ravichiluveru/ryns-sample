#!/bin/zsh
. ./scripts/include.sh "$0"
#========Do not change above===========
. ./scripts/generate-release-version.sh

cmd "./gradlew clean build publishToMavenLocal -x test -PdoRelease=true -PreleaseVersion=$releaseVersion"

#========Do not change below===========