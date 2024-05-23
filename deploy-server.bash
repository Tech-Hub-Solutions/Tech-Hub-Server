# var with key name
KEY_NAME="key.pem"
# array with ip addresses
IP_ADDRESSES=("10.0.0.247" "10.0.0.230")
# default user
USER="ubuntu"

# loop through the array
for ip in "${IP_ADDRESSES[@]}"
do
  # jump line
  printf "\n\n\nDeploying to $ip \n\n"
  echo "$(date) => Deploying to $ip" >> deploy-server.log
  # ssh into the server
  sudo ssh -i /home/$USER/.ssh/$KEY_NAME $USER@$ip '
    docker compose down
    docker compose pull
    docker compose up -d
  '
done
