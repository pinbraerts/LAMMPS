package com.pinbraerts.lammps;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.PsiComment;import com.intellij.psi.tree.IElementType;
import com.intellij.psi.TokenType;import com.intellij.util.containers.Stack;

%%

%class LAMMPSLexer
%implements FlexLexer
%function advance
%type IElementType
%eof{   return;
%eof}

NUMBER=[+-]? [:digit:]* "."? [:digit:]+
CRLF=[\r\n]
IDENTIFIER=[:letter:][:jletterdigit:]*
FILE_NAME=([:letter:]|[:digit:]|_|".")+
FILE_PATH={FILE_NAME}?([\/\\]+{FILE_NAME})+
COMMENT="#".*
SPACE=[ \t\f\u000C]

INITIALIZATION_COMMAND=
    "newton"
  | "package"
  | "processors"
  | "suffix"
  | "units"

SIMULATION_SETUP_COMMAND=
    "boundary"
  | box
  | "change_box"
  | "create_box"
  | "dimension"
  | "lattice"
  | "region"

ATOMS_SETUP_COMMAND=
    "atom_modify"
  | "atom_style"
  | "balance"
  | "create_atoms"
  | "create_bonds"
  | "delete_atoms"
  | "delete_bonds"
  | "displace_atoms"
  | "group"
  | "mass"
  | "molecule"
  | "replicate"
  | "set"
  | "velocity"

READ_FILE_COMMAND=
    "read_data"
  | "read_dump"
  | "read_restart"

FORCE_FIELDS_COMMAND=
    "angle_coeff"
  | "angle_style"
  | "bond_coeff"
  | "bond_style"
  | "bond_write"
  | "dielectric"
  | "dihedral_coeff"
  | "dihedral_style"
  | "improper_coeff"
  | "improper_style"
  | "kspace_modify"
  | "kspace_style"
  | "pair_coeff"
  | "pair_modify"
  | "pair_style"
  | "pair_write"
  | "special_bonds"

SETTINGS_COMMAND=
    "comm_modify"
  | "comm_style"
  | "info"
  | "min_modify"
  | "min_style"
  | "neigh_modify"
  | "neighbor"
  | "partition"
  | "reset_timestep"
  | "run_style"
  | "timer"
  | "timestep"

TIME_STEP_COMMAND=
    "compute"
  | "compute_modify"
  | "fix"
  | "fix_modify"
  | "uncompute"
  | "unfix"

OUTPUT_COMMAND=
    "dump image"
  | "dump movie"
  | "dump"
  | "dump_modify"
  | "restart"
  | "thermo"
  | "thermo_modify"
  | "thermo_style"
  | "undump"
  | "write_coeff"
  | "write_data"
  | "write_dump"
  | "write_restart"

ACTIONS_COMMAND=
    "minimize"
  | "neb"
  | "neb_spin"
  | "prd"
  | "rerun"
  | "run"
  | "tad"
  | "temper"

INPUT_SCRIPT_CONTROL_COMMAND=
    "clear"
  | "echo"
  | "if"
  | "include"
  | "jump"
  | "label"
  | "log"
  | "next"
  | "print"
  | "python"
  | "quit"
  | "shell"
  | "variable"

COMMAND=
    {INITIALIZATION_COMMAND}
  | {SIMULATION_SETUP_COMMAND}
  | {ATOMS_SETUP_COMMAND}
  | {FORCE_FIELDS_COMMAND}
  | {SETTINGS_COMMAND}
  | {TIME_STEP_COMMAND}
  | {OUTPUT_COMMAND}
  | {ACTIONS_COMMAND}
  | {INPUT_SCRIPT_CONTROL_COMMAND}

%s EXPECT_FILENAME

%%

<YYINITIAL> {
    // util
    {CRLF} { return LAMMPSTypes.CRLF; }
    {COMMENT} { return LAMMPSTypes.COMMENT; }
    {SPACE}+ { return TokenType.WHITE_SPACE; }

    // operators
    [+-] { return LAMMPSTypes.ADD_OPERATOR; }
    [*/] { return LAMMPSTypes.MUL_OPERATOR; }
    "$" { return LAMMPSTypes.DOLLAR_SIGN; }
    "&" { return LAMMPSTypes.AMPERSAND; }

    // identifiers
    {COMMAND} { return LAMMPSTypes.COMMAND; }
    {READ_FILE_COMMAND} { yybegin(EXPECT_FILENAME); return LAMMPSTypes.COMMAND; }
    {IDENTIFIER} { return LAMMPSTypes.IDENTIFIER; }

    // literals
    {NUMBER} { return LAMMPSTypes.NUMBER; }
    \" \\\" | [^\$]* \" { return LAMMPSTypes.STRING; }

    // braces
    "(" { return LAMMPSTypes.OPEN_PARENTHESIS; }
    ")" { return LAMMPSTypes.CLOSE_PARENTHESIS; }
    "{" { return LAMMPSTypes.OPEN_BRACE; }
    "}" { return LAMMPSTypes.CLOSE_BRACE; }
    "[" { return LAMMPSTypes.OPEN_BRACKET; }
    "]" { return LAMMPSTypes.CLOSE_BRACKET; }
}

<EXPECT_FILENAME> {
    {SPACE}+ { return TokenType.WHITE_SPACE; }
    {FILE_PATH} { yybegin(YYINITIAL); return LAMMPSTypes.FILE_PATH; }
}

[^] { return TokenType.BAD_CHARACTER; }
