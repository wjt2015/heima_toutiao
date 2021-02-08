#!/bin/sh
echo "argc=" $#
if (($# > 0));then
git add -A;git commit -m '$1';git push
else
git add -A;git commit -m 'update';git push
fi


