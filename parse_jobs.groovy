@GrabConfig(systemClassLoader=true)
@Grab(group='com.github.jsqlparser', module='jsqlparser', version='1.2')
@Grab(group='org.eclipse.jgit', module='org.eclipse.jgit', version='5.0.2.201807311906-r')

import groovy.io.FileType

import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statement
import net.sf.jsqlparser.statement.select.Select
import net.sf.jsqlparser.util.TablesNamesFinder

import org.eclipse.jgit.api.Git

// pull if exists?
//org.eclipse.jgit.api.errors.JGitInternalException
/*
Git git = Git.cloneRepository()
  .setURI( "https://github.com/sogis/gretljobs" )
  .setDirectory( new File("./gretljobs") )
  .call();
*/


def list = []

def dir = new File("./gretljobs")
dir.traverse (type: FileType.FILES, nameFilter: ~/(?i).*.sql/) { file ->
    list << file
}

list.each {
    println it.path
    def file = new File(it.path)
    def sql = file.text

    Statement stmt = CCJSqlParserUtil.parse(sql)
    Select selectStatement = (Select) stmt
    TablesNamesFinder tablesNamesFinder = new TablesNamesFinder()
    List<String> tableList = tablesNamesFinder.getTableList(selectStatement)

    println tableList
}


/*
def sql = """
SELECT 
    ogc_fid AS t_id,
    ST_Force_2D(wkb_geometry) AS geometrie,
    gnrso,
    location,
    abschnr,
    abstnr,
    absttyp,
    abstmat,
    absthoeh,
    erhebungsdatum,
    code_text.text AS absttyp_txt,
    abstmat.text AS abstmat_txt
FROM 
    gewisso.oeko_abstuerze
    LEFT JOIN gewisso.code_text
        ON 
            code_text.code = oeko_abstuerze.absttyp 
            AND 
            code_typ_id = 2
    LEFT JOIN gewisso.code_text AS abstmat
        ON 
            abstmat.code = oeko_abstuerze.abstmat 
            AND 
            abstmat.code_typ_id = 3
WHERE 
    archive = 0
;
"""

sql = """
WITH foo AS (
    SELECT a::int FROM bar
)
SELECT * FROM foo
;
"""

Statement stmt = CCJSqlParserUtil.parse(sql)
Select selectStatement = (Select) stmt
TablesNamesFinder tablesNamesFinder = new TablesNamesFinder()
List<String> tableList = tablesNamesFinder.getTableList(selectStatement)

*/

println tableList
