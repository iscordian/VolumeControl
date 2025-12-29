#!/usr/bin/env sh

DIR="$(cd "$(dirname "$0")" && pwd)"

JAVA_CMD="java"

if [ ! -x "$JAVA_CMD" ]; then
  JAVA_CMD="/usr/bin/java"
fi

exec "$JAVA_CMD" -jar "$DIR/gradle/wrapper/gradle-wrapper.jar" "$@"
