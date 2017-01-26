<MML 1.00 A sample mml file>
<%@page contentType="text/mml"%>

<Comment *** Include the font, paragraph, document definitions
   from another file.  By keeping the formats in different files
   than the document text, all documents can be assigned a new
   format by just changing one file.>

<Include "formats.mml">

<Comment *** Define a few macros just to show how it is done.  
   Would normally put such standard macros in an include file.>
<!DefineMacro 		if "<Italic>">
<!DefineMacro 		pf "<Plain>" >
<!DefineMacro 		bf "<Bold>"  >

<Comment *** Set up Headers and Footers. The next line sets the
   font.>
<HeaderFont <fhf>>
<LeftHeader 		"Maker Markup Language Specification">
<RightHeader 		"<%= new java.util.Date() %>">
<LeftFooter 		"Second Draft">
<RightFooter 		"Page #">

<Comment *** Start of Document Text ***>
<Title>
Maker Markup Language Specification
<Section>
Introduction
<Body>
Maker Markup Language (MML) is used to create formatted
FrameMaker documents from a text file. MML allows access to
many FrameMaker features.

<Comment *** The following Body paragraph contains an anchored
   frame.  The AFrame statement is equivalent to a MIF AFrame
   statement. (For a detailed description, see the online
   manual "MIF Reference.")  Inside the frame is a star. We
   just show this here so you can see how it is done.>

MML allows formatted documents to be created using both a
<if>GENCODE <pf>style of markup, in which document format and
content are separate notions,
<AFrame <BRect 0 0 4 2> <FrameType Below>
  <Polygon
   <Pen 0> <PenWidth `1.0'> <Fill 6> <Inverted No >
   <NumPoints 10>
   <Point  2.03" 0.29"> <Point  2.19" 0.83"> <Point  2.76" 0.83">
   <Point  2.28" 1.17"> <Point  2.49" 1.71"> <Point  2.03" 1.36">
   <Point  1.56" 1.71"> <Point  1.76" 1.15"> <Point  1.28" 0.83">
   <Point  1.86" 0.83">
  > # end of Polygon
> 
and a formatting style of markup, in which actual formatting
specifications are intermingled with the document text.

This document contains the following sections:
<BulletItem>
Instructions for creating MML documents

Overview of MML file format and syntax

Description of each MML Statement

Sample MML file

<Section>
Creating and Using MML Documents
<Body>
An MML document is a text file containing MML statements, text
broken up into paragraphs. It can be created using any text
editor.  It can also be created using Frame Maker: when saving
the document, specify Text Only in the Save dialog box.
