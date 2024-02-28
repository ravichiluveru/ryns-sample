
releaseVersion="$(date -u +'1.0.%y%m%d%H%M')"
echo "New RELEASE_VERSION: $releaseVersion"

echo "RELEASE_VERSION=${releaseVersion}" >>$GITHUB_ENV
echo "releaseVersion=${releaseVersion}" >>$GITHUB_OUTPUT