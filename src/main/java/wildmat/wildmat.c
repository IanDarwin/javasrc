/*
**  Do shell-style pattern matching for ?, \, [], and * characters.
**  Might not be robust in face of malformed patterns; e.g., "foo[a-"
**  could cause a segmentation violation.
**
**  Written by Rich $alz, mirror!rs, Wed Nov 26 19:03:17 EST 1986.
*/

#define TRUE		1
#define FALSE		0


static int
Star(s, p)
    register char	*s;
    register char	*p;
{
    while (wildmat(s, p) == FALSE)
	if (*++s == '\0')
	    return(FALSE);
    return(TRUE);
}


int
wildmat(s, p)
    register char	*s;
    register char	*p;
{
    register int 	 last;
    register int 	 matched;
    register int 	 reverse;

    for ( ; *p; s++, p++)
	switch (*p) {
	    case '\\':
		/* Literal match with following character; fall through. */
		p++;
	    default:
		if (*s != *p)
		    return(FALSE);
		continue;
	    case '?':
		/* Match anything. */
		if (*s == '\0')
		    return(FALSE);
		continue;
	    case '*':
		/* Trailing star matches everything. */
		return(*++p ? Star(s, p) : TRUE);
	    case '[':
		/* [^....] means inverse character class. */
		if (reverse = p[1] == '^')
		    p++;
		for (last = 0400, matched = FALSE; *++p && *p != ']'; last = *p)
		    /* This next line requires a good C compiler. */
		    if (*p == '-' ? *s <= *++p && *s >= last : *s == *p)
			matched = TRUE;
		if (matched == reverse)
		    return(FALSE);
		continue;
	}

    return(*s == '\0');
}


#ifdef	TEST
#include <stdio.h>

extern char	*gets();


main()
{
    char	 pattern[80];
    char	 text[80];

    while (TRUE) {
	printf("Enter pattern:  ");
	if (gets(pattern) == NULL)
	    break;
	while (TRUE) {
	    printf("Enter text:  ");
	    if (gets(text) == NULL)
		exit(0);
	    if (text[0] == '\0')
		/* Blank line; go back and get a new pattern. */
		break;
	    printf("      %d\n", wildmat(text, pattern));
	}
    }
    exit(0);
}
#endif	/* TEST */
