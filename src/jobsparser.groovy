@Grab(group='com.github.jsqlparser', module='jsqlparser', version='1.2')
@Grab(group='org.eclipse.jgit', module='org.eclipse.jgit', version='5.0.2.201807311906-r')

import groovy.io.FileType

import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statement
import net.sf.jsqlparser.statement.Statements
import net.sf.jsqlparser.statement.delete.Delete
import net.sf.jsqlparser.statement.insert.Insert
import net.sf.jsqlparser.statement.select.Select
import net.sf.jsqlparser.util.TablesNamesFinder

import org.eclipse.jgit.api.Git

def jobsRepository = "https://github.com/sogis/gretljobs"
def localCloneDirectory = "../gretljobs"

// Clone jobs repository.
// Existing directory will be deleted.
/*
new File(localCloneDirectory).deleteDir()
Git git = Git.cloneRepository()
        .setURI(jobsRepository)
        .setDirectory(new File(localCloneDirectory))
        .call()
*/

// At the moment some of the sql queries cannot
// be parsed with jsqlparser.
// Tricky: File name may not be unique.
// Use file path as reported in the loop,
// without repo name variable.
def sqlFilesBlacklist = []
sqlFilesBlacklist.add("/arp_mjpnatur_pub/arp_mjpnatur_pub_flaechen.sql")
sqlFilesBlacklist.add("/arp_naturreservate_pub/arp_naturreservate_pub_naturreservate_teilgebiete.sql")
sqlFilesBlacklist.add("/arp_naturreservate_pub/arp_naturreservate_pub_naturreservate_reservate.sql")
sqlFilesBlacklist.add("/agi_av_kaso_abgleich_pub/agi_av_kaso_abgleich_import_differenzen_staging.sql")
sqlFilesBlacklist.add("/agi_av_kaso_abgleich_pub/agi_av_kaso_abgleich_import_uebersicht_des_vergleichs_staging.sql")
sqlFilesBlacklist.add("/afu_isboden_pub/afu_isboden_pub_bodeneinheit_gesamt.sql")
sqlFilesBlacklist.add("/afu_isboden_pub/afu_isboden_pub_bodenprofilstandort.sql")
sqlFilesBlacklist.add("/amb_zivilschutz_adressen_export/amb_zivilschutz_adressen_staging_adressen_zivilschutz.sql")
sqlFilesBlacklist.add("/arp_aggloprogramme_pub/arp_aggloprogramme_pub_agglomrtnsprgrmme_massnahme.sql")
sqlFilesBlacklist.add("/agi_wmts_hetzner_seeder/createHoheitsgrenzenSchema.sql")
sqlFilesBlacklist.add("/agi_wmts_hetzner_seeder/createGrundbuchplanSchema.sql")
sqlFilesBlacklist.add("/afu_gewschutz_export_ai/export/gsareal_insert.sql")
sqlFilesBlacklist.add("/afu_gewschutz_export_ai/export/gsbereich_insert.sql")
sqlFilesBlacklist.add("/afu_gewschutz_export_ai/export/gszone_insert.sql")
sqlFilesBlacklist.add("/agi_hoheitsgrenzen_pub/agi_hoheitsgrenzen_pub_hoheitsgrenzen_gemeindegrenze.sql")
sqlFilesBlacklist.add("/awjf_biotopbaeume_pub/awjf_biotopbaeume_pub_biotopbaeume_biotopbaum.sql")
sqlFilesBlacklist.add("/afu_gewaesserschutz_pub/afu_gewaesserschutz_pub_aww_gszoar.sql")
sqlFilesBlacklist.add("/afu_gewaesserschutz_pub/afu_gewaesserschutz_pub_aww_gsab.sql")
sqlFilesBlacklist.add("/agi_av_gb_abgleich_pub/agi_av_gb_abgleich_import_uebersicht_des_vergleichs_staging.sql")
sqlFilesBlacklist.add("/agi_av_gb_abgleich_pub/agi_av_gb_abgleich_import_differenzen_staging.sql")

// Get all sql files in the clone repo.
def list = []
def dir = new File(localCloneDirectory)
dir.traverse (type: FileType.FILES, nameFilter: ~/(?i).*.sql/) { file ->
    list << file
}

// Loop through all sql files and parse the query.
list.each {
    println it.path

    if (sqlFilesBlacklist.contains(it.path.substring(localCloneDirectory.size()))) {
        println "Blacklist: " + it.path
        return
    }
    def file = new File(it.path)
    def sql = file.text

    Statements stmts = CCJSqlParserUtil.parseStatements(sql)
    for (Statement stmt : stmts.statements) {
        TablesNamesFinder tablesNamesFinder = new TablesNamesFinder()
        List<String> tableList = (tablesNamesFinder.getTableList(stmt))
        tableList.each { table ->
            println table
        }


    }

//    Select selectStatement = (Select) stmt
//    TablesNamesFinder tablesNamesFinder = new TablesNamesFinder()
//    List<String> tableList = tablesNamesFinder.getTableList(selectStatement)

    //println tableList
}
