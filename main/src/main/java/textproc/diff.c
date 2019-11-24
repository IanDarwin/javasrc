/***************************************************************************
*
* diff         Text file difference utility.
* ----         Copyright 1987, 1989 by Donald C. Lindsay,
*              School of Computer Science,  Carnegie Mellon University.
*              Copyright 1982 by Symbionics.
*              Use without fee is permitted when not for direct commercial
*              advantage, and when credit to the source is given. Other uses
*              require specific permission.
*
* USEAGE:      diff oldfile newfile
*
* This program assumes that "oldfile" and "newfile" are text files.
* The program writes to stdout a description of the changes which would
* transform "oldfile" into "newfile".
*
* The printout is in the form of commands, each followed by a block of
* text. The text is delimited by the commands, which are:
*
*    DELETE AT n
*         ..deleted lines
*
*    INSERT BEFORE n
*         ..inserted lines
*
*    n MOVED TO BEFORE n
*         ..moved lines
*
*    n CHANGED FROM
*         ..old lines
*    CHANGED TO
*         ..newer lines
*
* The line numbers all refer to the lines of the oldfile, as they are
*    numbered before any commands are applied.
* The text lines are printed as-is, without indentation or prefixing. The
*    commands are printed in upper case, with a prefix of ">>>>", so that
*    they will stand out. Other schemes may be preferred.
* Input lines which are longer than MAXLINELEN characters will be chopped 
*    into multiple lines.
* Files which contain more than MAXLINECOUNT lines cannot be processed.
* The algorithm is taken from Communications of the ACM, Apr78 (21, 4, 264-),
*    "A Technique for Isolating Differences Between Files."
*    Ignoring I/O, and ignoring the symbol table, it should take O(N) time.
*    This implementation takes fixed space, plus O(U) space for the symbol
*    table (where U is the number of unique lines). Methods exist to change
*    the fixed space to O(N) space.
* Note that this is not the only interesting file-difference algorithm. In
*    general, different algorithms draw different conclusions about the
*    changes that have been made to the oldfile. This algorithm is sometimes
*    "more right", particularly since it does not consider a block move to be 
*    an insertion and a (separate) deletion. However, on some files it will be
*    "less right". This is a consequence of the fact that files may contain
*    many identical lines (particularly if they are program source). Each
*    algorithm resolves the ambiguity in its own way, and the resolution
*    is never guaranteed to be "right". However, it is often excellent.
* This program is intended to be pedagogic.  Specifically, this program was
*    the basis of the Literate Programming column which appeared in the
*    Communications of the ACM (CACM), in the June 1989 issue (32, 6,
*    740-755).
* By "pedagogic", I do not mean that the program is gracefully worded, or
*    that it showcases language features or its algorithm. I also do not mean
*    that it is highly accessible to beginners, or that it is intended to be
*    read in full, or in a particular order. Rather, this program is an
*    example of one professional's style of keeping things organized and
*    maintainable.
* This program is a de-engineered version of a program which uses less
*    memory and less time.  The article points out that the "symbol" arrays
*    can be implemented as arrays of pointers to arrays, with dynamic
*    allocation of the subarrays.  (Macros are very useful for hiding the
*    two-level accesses.) This allows an extremely large value for
*    MAXLINECOUNT, without dedicating fixed arrays. (The "other" and
*    "blocklen" arrays can be allocated after the input phase, when the exact
*    sizes are known.) The only slow piece of code is the "strcmp" in the tree
*    descent: it can be speeded up by keeping a hash in the tree node, and
*    only using "strcmp" when two hashes happen to be equal.
* This program has been coded with 5-column tab settings.
*
* Change Log
* ----------
* 10jun89 D.C.Lindsay, CMU: posted version created.
*         Copyright notice changed to ACM style, and Dept. is now School.
*         ACM article referenced in docn.
* 26sep87 D.C.Lindsay, CMU: publication version created.
*         Condensed all 1982/83 change log entries.
*         Removed all command line options, and supporting code. This 
*         simplified the input code (no case reduction etc). It also
*         simplified the symbol table, which was capable of remembering
*         offsets into files (instead of strings), and trusting (!) hash
*         values to be unique.
*         Removed dynamic allocation of arrays: now fixed static arrays.
*         Removed speed optimizations in symtab package.
*         Removed string compression/decompression code.
*         Recoded to Unix standards from old Lattice/MSDOS standards.
*         (This affected only the #include's and the IO.)
*         Some renaming of variables, and rewording of comments.
* 1982/83 D.C.Lindsay, Symbionics: created.
*
*/

#define MAXLINECOUNT 20000         /* arbitrary */
#define MAXLINELEN  255            /* arbitrary */

#include <stdio.h>
/************************************************/
#define UNREAL (MAXLINECOUNT+2)  /* block len > any possible real block len */

/************************************************/

struct info{                         /* This is the info kept per-file.     */
     FILE *file;                     /* File handle that is open for read.  */
     int maxline;                    /* After input done, # lines in file.  */
     char *symbol[ MAXLINECOUNT+2 ]; /* The symtab handle of each line.     */
     int other   [ MAXLINECOUNT+2 ]; /* Map of line# to line# in other file */
                                     /* ( -1 means don't-know ).            */
} oldinfo, newinfo;

int blocklen[ MAXLINECOUNT+2 ];
/* The above is the info about found blocks. It will be set to 0, except
*  at the line#s where blocks start in the old file. At these places it
*  will be set to the # of lines in the block. During the printout phase,
*  this value will be reset to -1 if the block is printed as a MOVE block.
*  (This is because the printout phase will encounter the block twice, but
*  must only print it once. )
*  The array declarations are to MAXLINECOUNT+2 so that we can have two
*  extra lines (pseudolines) at line# 0 and line# MAXLINECOUNT+1 (or less).
*/

               /* function predeclarations */
               /* (These could be eliminated by a reordering) */
FILE *openfile();
void inputscan();
void storeline();
void transform();
void scanunique();
void scanafter();
void scanbefore();
void scanblocks();
void printout();
void newconsume();
void oldconsume();
void showdelete();
void showinsert();
void showchange();
void skipold();
void showsame();
void showmove();
void initsymtab();
char *addsymbol();
int symbolisunique();
int lineofsymbol();
void showsymbol();
/***************************************************************************
*
* main         Entry point.
* ----
*
* NOTE: no routines return error codes. Instead, any routine may complain
*       to stderr and then exit with error to the system. This property 
*       is not mentioned in the various routine headers.
*
***************************************************************************/
main( argcount, argstrings )
int argcount;
char *argstrings[];
{
     if( argcount != 3 ) {
          fprintf( stderr, "TRY: diff oldfile newfile\n\007" );  /* (bel) */
          exit(1);
     }
     printf( ">>>> Difference of file \"%s\" and file \"%s\".\n\n",
          argstrings[1], argstrings[2] );
     initsymtab();
     oldinfo.file = openfile( argstrings[1] );
     newinfo.file = openfile( argstrings[2] );
     /* note, we don't process until we know both files really do exist. */
     inputscan( &oldinfo );
     inputscan( &newinfo );
     transform();
     printout();
     exit(0);       /* OK */
}
/***************************************************************************
*
* openfile     Opens the filename for reading.
* --------     Returns the file handle.
*
***************************************************************************/
FILE *openfile( filename )
char *filename;
{
     FILE *file = fopen( filename, "r" );
     if( file == NULL ) {
          fprintf( stderr, "CAN'T OPEN FILE %s\n\007", filename );  /* (bel) */
          exit(1);
     }
     return file;
}
/***************************************************************************
*
* inputscan    Reads the file specified by pinfo->file.
* ---------    Places the lines of that file in the symbol table.
*              Sets pinfo->maxline to the number of lines found.
*              Expects initsymtab has been called.
*
****************************************************************************/
void inputscan( pinfo )
struct info *pinfo;
{
     char ch, linebuffer[ MAXLINELEN+1 ];    /* accumulate lines to here */
     int linelen = 0;                        /* # of chars in linebuffer */

     pinfo-> maxline = 0;
     for(;;) {
          ch = getc( pinfo-> file );
          if( ch == EOF ) break;
          if( ch == '\n' ){                                 /* end of line */
               storeline( linebuffer, linelen, pinfo );
               linelen = 0;
          }else if( linelen >= MAXLINELEN ) {               /* overflow */
               storeline( linebuffer, linelen, pinfo );
               linelen = 1;
               linebuffer[ 0 ] = ch;                   /* start next line */
          }else linebuffer[ linelen++ ] = ch;
     }
     if( linelen != 0 ) storeline( linebuffer, linelen, pinfo );  /* runt */
}
/**************************************************************************
*
* storeline    Places line into symbol table.
* ---------    Expects pinfo-> maxline initted: increments.
*              Places symbol table handle in pinfo->symbol.
*              Expects pinfo is either &oldinfo or &newinfo.
*              Expects linebuffer contains linelen nonnull chars.
*              Expects linebuffer has room to write a trailing nul into.
*              Expects initsymtab has been called.
*
***************************************************************************/
void storeline( linebuffer, linelen, pinfo )
char linebuffer[];
int linelen;
struct info *pinfo;
{
     int linenum = ++( pinfo-> maxline );    /* note, no line zero */
     if( linenum > MAXLINECOUNT ) {
          fprintf( stderr, "MAXLINECOUNT exceeded, must stop.\n\007" );
          exit(1);
     }
     linebuffer[ linelen ] = '\0';           /* nul terminate */
     pinfo-> symbol[ linenum ] =
          addsymbol( linebuffer, linelen, pinfo == &oldinfo, linenum );
}
/***************************************************************************
*
* transform    Expects both files in symtab.
* ---------    Expects valid "maxline" and "symbol" in oldinfo and newinfo.
*              Analyzes the file differences and leaves its findings in
*              the global arrays oldinfo.other, newinfo.other, and blocklen.
*
***************************************************************************/
void transform()
{                                  
     int oldline, newline;
     int oldmax = oldinfo.maxline + 2;  /* Count pseudolines at  */
     int newmax = newinfo.maxline + 2;  /* ..front and rear of file */

     for(oldline=0; oldline < oldmax; oldline++ ) oldinfo.other[oldline]= -1;
     for(newline=0; newline < newmax; newline++ ) newinfo.other[newline]= -1;

     scanunique();  /* scan for lines used once in both files */
     scanafter();   /* scan past sure-matches for non-unique blocks */
     scanbefore();  /* scan backwards from sure-matches */
     scanblocks();  /* find the fronts and lengths of blocks */
}
/***************************************************************************
*
* scanunique   Expects both files in symtab, and oldinfo and newinfo valid.
* ----------   Scans for lines which are used exactly once in each file.
*              The appropriate "other" array entries are set to the line# in
*              the other file.
*              Claims pseudo-lines at 0 and XXXinfo.maxline+1 are unique.
*
***************************************************************************/
void scanunique()
{
     int oldline, newline;
     char *psymbol;

     for( newline = 1; newline <= newinfo.maxline; newline++ ) {
          psymbol = newinfo.symbol[ newline ];
          if( symbolisunique( psymbol )) {        /* 1 use in each file */
               oldline = lineofsymbol( psymbol );
               newinfo.other[ newline ] = oldline;     /* record a 1-1 map */
               oldinfo.other[ oldline ] = newline;
          }
     }
     newinfo.other[ 0 ] = 0;
     oldinfo.other[ 0 ] = 0;
     newinfo.other[ newinfo.maxline + 1 ] = oldinfo.maxline + 1;
     oldinfo.other[ oldinfo.maxline + 1 ] = newinfo.maxline + 1;
}
/***************************************************************************
*
* scanafter    Expects both files in symtab, and oldinfo and newinfo valid.
* ---------    Expects the "other" arrays contain positive #s to indicate
*              lines that are unique in both files.
*              For each such pair of places, scans past in each file.
*              Contiguous groups of lines that match non-uniquely are
*              taken to be good-enough matches, and so marked in "other".
*              Assumes each other[0] is 0.
*
***************************************************************************/
void scanafter()
{
     int oldline, newline;

     for( newline = 0; newline <= newinfo.maxline; newline++ ) {
          oldline = newinfo.other[ newline ];
          if( oldline >= 0 ) {          /* is unique in old & new */
               for(;;) {                /* scan after there in both files */
                    if( ++oldline > oldinfo.maxline   ) break; 
                    if( oldinfo.other[ oldline ] >= 0 ) break;
                    if( ++newline > newinfo.maxline   ) break; 
                    if( newinfo.other[ newline ] >= 0 ) break;

                    /* oldline & newline exist, and aren't already matched */

                    if( newinfo.symbol[ newline ] !=
                        oldinfo.symbol[ oldline ] ) break;  /* not same */

                    newinfo.other[ newline ] = oldline;   /* record a match */
                    oldinfo.other[ oldline ] = newline;
               }
          }
     }
}
/***************************************************************************
*
* scanbefore   As scanafter, except scans towards file fronts.
* ----------   Assumes the off-end lines have been marked as a match.
*
***************************************************************************/
void scanbefore()
{
     int oldline, newline;

     for( newline = newinfo.maxline + 1; newline > 0; newline-- ) {
          oldline = newinfo.other[ newline ];
          if( oldline >= 0 ) {               /* unique in each */
               for(;;) {
                    if( --oldline <= 0                ) break;
                    if( oldinfo.other[ oldline ] >= 0 ) break;
                    if( --newline <= 0                ) break;
                    if( newinfo.other[ newline ] >= 0 ) break;
     
                    /* oldline and newline exist, and aren't marked yet */

                    if( newinfo.symbol[ newline ] !=
                        oldinfo.symbol[ oldline ] ) break;  /* not same */

                    newinfo.other[ newline ] = oldline;   /* record a match */
                    oldinfo.other[ oldline ] = newline;
               }
          }
     }
}
/***************************************************************************
*
* scanblocks   Expects oldinfo valid.
* ----------   Finds the beginnings and lengths of blocks of matches.
*              Sets the blocklen array (see definition).
*
***************************************************************************/
void scanblocks()
{
     int oldline, newline;
     int oldfront = 0;      /* line# of front of a block in old file, or 0  */
     int newlast = -1;      /* newline's value during the previous iteration*/

     for( oldline = 1; oldline <= oldinfo.maxline; oldline++ )
               blocklen[ oldline ] = 0;
     blocklen[ oldinfo.maxline + 1 ] = UNREAL;    /* starts  a mythical blk */

     for( oldline = 1; oldline <= oldinfo.maxline; oldline++ ) {
          newline = oldinfo.other[ oldline ];
          if( newline < 0 ) oldfront = 0;         /* no match: not in block */
          else{                                   /* match. */
               if( oldfront == 0 )         oldfront = oldline;
               if( newline != (newlast+1)) oldfront = oldline;
               ++blocklen[ oldfront ];            
          }
          newlast = newline;
     }
}
/***************************************************************************
*
* printout     Expects all data structures have been filled out.
* --------     Prints summary to stdout.
*
***************************************************************************/
          /* The following are global to printout's subsidiary routines */

enum{ idle, delete, insert, movenew, moveold, same, change } printstatus;
enum{ false, true } anyprinted;
int printoldline, printnewline;         /* line numbers in old & new file */

void printout()
{
     printstatus = idle;
     anyprinted = false;
     for( printoldline = printnewline = 1; ; ) {
          if( printoldline > oldinfo.maxline ) { newconsume(); break;}
          if( printnewline > newinfo.maxline ) { oldconsume(); break;}
          if(      newinfo.other[ printnewline ] < 0 ) {
               if( oldinfo.other[ printoldline ] < 0 )           showchange();
               else                                              showinsert();
          }
          else if( oldinfo.other[ printoldline ] < 0 )           showdelete();
          else if( blocklen[ printoldline ] < 0 )                  skipold();
          else if( oldinfo.other[ printoldline ] == printnewline ) showsame();
          else                                                     showmove();
     }
     if( anyprinted == true ) printf( ">>>> End of differences.\n"  );
     else                     printf( ">>>> Files are identical.\n" );
}
/***************************************************************************
*
* newconsume        Part of printout. Have run out of old file. 
* ----------        Print the rest of the new file, as inserts and/or moves.
*
***************************************************************************/
void newconsume()
{
     for(;;) {
          if( printnewline > newinfo.maxline ) break;        /* end of file */
          if( newinfo.other[ printnewline ] < 0 ) showinsert();
          else                                    showmove();
     }
}
/***************************************************************************
*
* oldconsume        Part of printout. Have run out of new file.
* ----------        Process the rest of the old file, printing any
*                   parts which were deletes or moves.
*
***************************************************************************/
void oldconsume()
{
     for(;;) {
          if( printoldline > oldinfo.maxline ) break;       /* end of file */
          printnewline = oldinfo.other[ printoldline ];
          if( printnewline < 0 ) showdelete();
          else if( blocklen[ printoldline ] < 0 ) skipold();
          else showmove();
     }
}
/***************************************************************************
*
* showdelete        Part of printout.
* ----------        Expects printoldline is at a deletion.
*
***************************************************************************/
void showdelete()
{
     if( printstatus != delete ) printf( ">>>> DELETE AT %d\n", printoldline);
     printstatus = delete;
     showsymbol( oldinfo.symbol[ printoldline ]);
     anyprinted = true;
     printoldline++;
}
/***************************************************************************
*
* showinsert        Part of printout.
* ----------        Expects printnewline is at an insertion.
*
***************************************************************************/
void showinsert()
{
     if( printstatus == change ) printf( ">>>>     CHANGED TO\n" );
     else if( printstatus != insert ) 
          printf( ">>>> INSERT BEFORE %d\n", printoldline );
     printstatus = insert;
     showsymbol( newinfo.symbol[ printnewline ]);
     anyprinted = true;
     printnewline++;
}
/***************************************************************************
*
* showchange        Part of printout.
* ----------        Expects printnewline is an insertion.
*                   Expects printoldline is a deletion.
*
***************************************************************************/
void showchange()
{
     if( printstatus != change ) 
          printf( ">>>> %d CHANGED FROM\n", printoldline );
     printstatus = change;
     showsymbol( oldinfo.symbol[ printoldline ]);
     anyprinted = true;
     printoldline++;
}
/***************************************************************************
*
* skipold           Part of printout.
* -------           Expects printoldline at start of an old block that has 
*                   already been announced as a move.
*                   Skips over the old block.
*
***************************************************************************/
void skipold()
{
     printstatus = idle;
     for(;;) {
          if( ++printoldline > oldinfo.maxline ) break;     /* end of file  */
          if( oldinfo.other[ printoldline ] < 0 ) break;    /* end of block */
          if( blocklen[ printoldline ]) break;          /* start of another */
     }
}
/***************************************************************************
*
* skipnew           Part of printout.
* -------           Expects printnewline is at start of a new block that has
*                   already been announced as a move.
*                   Skips over the new block.
*
***************************************************************************/
void skipnew()
{
     int oldline;
     printstatus = idle;
     for(;;) {
          if( ++printnewline > newinfo.maxline ) break;    /* end of file  */
          oldline = newinfo.other[ printnewline ];
          if( oldline < 0 ) break;                         /* end of block */
          if( blocklen[ oldline ]) break;              /* start of another */

     }
}
/***************************************************************************
*
* showsame          Part of printout.
* --------          Expects printnewline and printoldline at start of
*                   two blocks that aren't to be displayed.
*
***************************************************************************/
void showsame()
{
     int count;
     printstatus = idle;
     if( newinfo.other[ printnewline ] != printoldline ) {
          fprintf( stderr, "BUG IN LINE REFERENCING\n\007" );     /* (bel) */
          exit(1);
     }
     count = blocklen[ printoldline ];
     printoldline += count;
     printnewline += count;
}
/***************************************************************************
*
* showmove          Part of printout.
* --------          Expects printoldline, printnewline at start of
*                   two different blocks ( a move was done).
*
***************************************************************************/
void showmove()
{
     int oldblock = blocklen[ printoldline ];
     int newother = newinfo.other[ printnewline ];
     int newblock = blocklen[ newother ];

     if( newblock < 0 ) skipnew();           /* already printed */
     else if( oldblock >= newblock ) {       /* assume new's blk moved */
          blocklen[ newother ] = -1;         /* stamp block as "printed" */
          printf( ">>>> %d MOVED TO BEFORE %d\n", newother, printoldline );
          for( ; newblock > 0; newblock--, printnewline++ )
               showsymbol( newinfo.symbol[ printnewline ]);
          anyprinted = true;
          printstatus = idle;

     }else                         /* assume old's block moved */
          skipold();               /* target line# not known, display later */

}
/***************************************************************************
*
* The symbol table routines follow. They are a "package" and all
* understand the symbol table format, which is a binary tree.
* The "entry points" are: initsymtab, addsymbol, symbolisunique,
* lineofsymbol, and showsymbol.
*
***************************************************************************/

enum linestates{ freshnode, oldonce, newonce, bothonce, other };

struct node{                       /* the tree is made up of these nodes */
     struct node *pleft, *pright;
     int linenum;
     enum linestates linestate;
     /* The text of the line is stored after this, as a varying-length
     *  nul-terminated string.
     */
};
struct node *panchor;    /* symtab is a tree hung from this */

/* The following macro computes the address of the string part of a node. */
#define PTEXT( PNODE )   (sizeof( struct node) + (char *)PNODE)
/***************************************************************************
*
* initsymtab        Must be called, once, before any calls to addsymbol.
* ----------
*
***************************************************************************/
void initsymtab()
{
     panchor = NULL;
}
/***************************************************************************
*
* newnode        Allocates a new symbol table node and fills in its fields.
* -------        Expects pline -> a nul-terminated string of linelen 
*                non-nul characters. Copies this string into the new node.
*                Sets linestate = freshnode. Does not set linenum.
*                Returns a pointer to the new node.
*
***************************************************************************/
struct node *newnode( pline, linelen )
char *pline;
int linelen;
{
     struct node *new = 
          (struct node *) malloc( 1 + linelen + sizeof( struct node ));
     if( new == NULL ) { 
          fprintf( stderr, "Out of memory, sorry.\n\007");       /* (bel) */
          exit(1);
     }
     new-> pleft = new-> pright = NULL;
     new-> linestate = freshnode;
     /* linenum field is not always valid */     
     strcpy( PTEXT( new ), pline );
     return new;
}
/***************************************************************************
*
* matchsymbol       Searches tree for a match to the line.
* ----------        Expects pline-> a nul-terminated string of linelen
*                   non-null chars.
*                   Returns a ptr to a matching node.
*                   If node's linestate == freshnode, then created the node.
*
***************************************************************************/
struct node *matchsymbol( pline, linelen )
char *pline;
int linelen;
{
     int comparison;
     struct node *pnode = panchor;
     if( panchor == NULL ) return panchor = newnode( pline, linelen );
     for(;;) {
          comparison = strcmp( PTEXT(pnode), pline );
          if( comparison == 0 ) return pnode;          /* found */

          if( comparison < 0 ) {
               if( pnode-> pleft == NULL ) {
                    pnode->pleft = newnode( pline, linelen );
                    return pnode-> pleft;
               }
               pnode = pnode-> pleft;
          }
          if( comparison > 0 ) {
               if( pnode-> pright == NULL ) {
                    pnode->pright = newnode( pline, linelen );
                    return pnode-> pright;
               }
               pnode = pnode-> pright;
          }
     }
     /* NOTE: There are return stmts, so control does not get here. */
}
/***************************************************************************
*
* addsymbol    Expects pline-> a string with linelen non-nul chars.
* ---------    Saves that line into the symbol table.
*              Returns a handle to the symtab entry for that unique line.
*              If inoldfile nonzero, then linenum is remembered.
*              Expects initsymbtab has been called, once.
*
****************************************************************************/
char *addsymbol( pline, linelen, inoldfile, linenum )
char *pline;
int linelen, inoldfile, linenum;
{
     struct node *pnode;
     pnode = matchsymbol( pline, linelen );  /* find the node in the tree */
     if( pnode-> linestate == freshnode ) {
          pnode-> linestate = inoldfile ? oldonce : newonce;
     }else{
          if(( pnode-> linestate == oldonce && !inoldfile ) ||
             ( pnode-> linestate == newonce &&  inoldfile )) 
               pnode-> linestate = bothonce;
          else pnode-> linestate = other;
     }
     if( inoldfile ) pnode-> linenum = linenum;
     return (char *)pnode;
}
/***************************************************************************
*
* symbolisunique    Arg is a ptr previously returned by addsymbol.
* --------------    Returns true if the line was added to the
*                   symbol table exactly once with inoldfile true,
*                   and exactly once with inoldfile false.
*
***************************************************************************/
int symbolisunique( psymbol )
struct node *psymbol;
{
     return (psymbol-> linestate == bothonce );
}
/***************************************************************************
*
* lineofsymbol      Arg is a ptr previously returned by addsymbol.
* ------------      Returns the line number stored with the line.
*
***************************************************************************/
int lineofsymbol( psymbol )
struct node *psymbol;
{
     return psymbol-> linenum;
}
/***************************************************************************
*
* showsymbol        Arg is a ptr previously returned by addsymbol.
* ----------        Prints the line to stdout.
*
***************************************************************************/
void showsymbol( psymbol )
struct node *psymbol;
{
     printf( "%s\n", PTEXT( psymbol ) );
}
/***************************************************************************/
