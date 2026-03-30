#!/bin/bash
RASPBERRY="mezzanine@raspberrypi"
DEPLOY_DIR="/home/mezzanine/apps"

echo "Building Spring Boot..."
cd backend
mvn clean package -DskipTests
cd ..

echo "Copying to Raspberry..."
ssh $RASPBERRY "mkdir -p $DEPLOY_DIR/panopsys"
scp backend/target/panopsys-backend-0.0.1-SNAPSHOT.jar $RASPBERRY:$DEPLOY_DIR/panopsys/panopsys.jar

echo "Restarting service..."
ssh $RASPBERRY "sudo systemctl restart panopsys"

echo "Done!"