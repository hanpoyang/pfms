#!/bin/sh
echo --------------------
echo $1
echo $2
echo $3
echo --------------------
output=`expect <<EOF
spawn ssh $1 $2
expect "*password:"
send "$3\r"
expect "yes/no"
send "yes\r"
expect "*#"
EOF`
echo output=$output
