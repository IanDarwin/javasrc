for f do
echo $f|tr / ' ' | while read dir java
do
ed $f <<!
0a
package $dir;

.
w
q
!
done
done
