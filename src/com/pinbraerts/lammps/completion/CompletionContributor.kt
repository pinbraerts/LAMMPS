package com.pinbraerts.lammps.completion

import com.intellij.codeInsight.completion.*
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.patterns.PlatformPatterns
import com.intellij.util.ProcessingContext
import com.pinbraerts.lammps.LAMMPSFile

class LAMMPSCompletionContributor: CompletionContributor() {
    companion object {
        val COMMANDS = arrayOf(
                "newton", "package", "processors", "suffix", "units", "boundary", "change_box", "create_box",
                "dimension", "lattice", "region", "atom_modify", "atom_style", "balance", "create_atoms",
                "create_bonds", "delete_atoms", "delete_bonds", "displace_atoms", "group", "mass", "molecule",
                "replicate", "set", "velocity", "read_data", "read_dump", "read_restart", "angle_coeff", "angle_style",
                "bond_coeff", "bond_style", "bond_write", "dielectric", "dihedral_coeff", "dihedral_style",
                "improper_coeff", "improper_style", "kspace_modify", "kspace_style", "pair_coeff", "pair_modify",
                "pair_style", "pair_write", "special_bonds", "comm_modify", "comm_style", "info", "min_modify",
                "min_style", "neigh_modify", "neighbor", "partition", "reset_timestep", "run_style", "timer",
                "timestep", "compute", "compute_modify", "fix", "fix_modify", "uncompute", "unfix", "dump image",
                "dump movie", "dump", "dump_modify", "restart", "thermo", "thermo_modify", "thermo_style", "undump",
                "write_coeff", "write_data", "write_dump", "write_restart", "minimize", "neb", "neb_spin", "prd",
                "rerun", "run", "tad", "temper", "clear", "echo", "if", "include", "jump", "label", "log", "next",
                "print", "python", "quit", "shell", "variable"
        ).map(LookupElementBuilder::create)
    }

    init {
        extend(CompletionType.BASIC,
                PlatformPatterns.psiElement(),
                object: CompletionProvider<CompletionParameters>() {
                    override fun addCompletions(
                            parameters: CompletionParameters,
                            context: ProcessingContext,
                            result: CompletionResultSet) {
                        result.addAllElements(COMMANDS)
                    }
                }
        )
    }
}