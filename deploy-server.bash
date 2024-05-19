# var with key name
KEY_NAME="key.pem"
# array with ip addresses
IP_ADDRESSES=("10.0.0.0" "10.0.0.0")
# default user
USER="ubuntu"

# loop through the array
for ip in "${IP_ADDRESSES[@]}"
do
  echo "Deploying to $ip"
  # ssh into the server
  sudo ssh -i /home/$USER/.ssh/$KEY_NAME $USER@$ip '
    docker compose down
    docker compose pull
    docker compose up -d
  '
  # add in log file
  echo "Deployed to $ip" >> deploy-server.log
done
