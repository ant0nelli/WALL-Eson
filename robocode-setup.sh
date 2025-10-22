#!/bin/bash
set -e  

curl -L -o ~/Downloads/robocode-setup.jar https://sourceforge.net/projects/robocode/files/latest/download

java -jar ~/Downloads/robocode-setup.jar

cat << 'EOF' > ~/robocode/robocode.sh
#!/bin/sh
#
# Copyright (c) 2001-2025 Mathew A. Nelson and Robocode contributors
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v1.0
# which accompanies this distribution, and is available at
# https://robocode.sourceforge.io/license/epl-v10.html
#

#----------------------------------------------------------
# Run Robocode (UI)
#----------------------------------------------------------

# Used for setting Java options
./set_java_options.sh
exit_code=$?

# Save present work directory (pwd)
pwd=$(pwd)

# Change directory to the directory where this script is located
cd "${0%/*}" || exit

if [ "$exit_code" -ne 100 ]; then
  java \
    -cp "libs/*" \
    -Xmx512M \
    -Xdock:name=Robocode \
    -Xdock:icon=robocode.ico \
    -XX:+IgnoreUnrecognizedVMOptions \
    "--add-opens=java.base/sun.net.www.protocol.jar=ALL-UNNAMED" \
    "--add-opens=java.base/java.lang.reflect=ALL-UNNAMED" \
    "--add-opens=java.desktop/javax.swing.text=ALL-UNNAMED" \
    "--add-opens=java.desktop/sun.awt=ALL-UNNAMED" \
    robocode.Robocode "$@"
fi

cd "${pwd}" || exit
EOF

mkdir -p ~/robocode/robots/ifsc/WALLEson/

mv W* ~/robocode/robots/ifsc/WALLEson/

rm -rf ~/Downloads/robocode-setup.jar
rm -f robocode-setup.sh

~/robocode/robocode.sh
