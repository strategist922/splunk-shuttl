#!/bin/sh

argsLength=$#
if [[ $argsLength < 3 ]]; then
    echo "Not enought arguments!"
    exit 1
fi

splunkPath=$1
filePath=$2
index=$3
if [[ $argsLength == 4 ]]; then
    login=$4
else
    login="admin:changeme"
fi
loremIpsum=$filePath/loremIpsum.txt


echo "\$0 = $0"
echo "\$splunkPath = $splunkPath"
echo "\$filePath = $filePath"
echo "\$loremIpsum = $loremIpsum"
echo "\$index = $index"
echo "\$login = $login"
echo "----------------------------"

ret_code=0
if [[ ! -f $loremIpsum ]]; then
	
	curl http://loripsum.net/api/1000/verylong > $loremIpsum || ret_code=1
	echo ""

	if [[ $ret_code != 0 ]]; then
		echo "Error when getting input data!"
		exit $ret_code
	else
		echo "Got some random input data!\n"
	fi
fi

i=0
while [ $i -lt 20 -a $ret_code -eq 0 ]; do 
	$splunkPath/bin/splunk add oneshot $loremIpsum -index $index -auth $login || ret_code=1
	let i++
done

test $ret_code -eq 0 && echo "\nAdded $i oneshots to splunk!"
exit $ret_code
