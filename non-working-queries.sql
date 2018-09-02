SELECT
	ogc_fid,
	COALESCE((((dump).path)[1]),0) AS partindex,
	ST_AsBinary((dump).geom) AS singlepoly_wkb
FROM
(
	SELECT 
		ST_DUMP(wkb_geometry) AS dump, 
		ogc_fid 
	FROM 
		afu_gszoar
) AS dump
;
SELECT
	new_date::date::character varying::text
FROM
	flachen
;
SELECT
	position('/opt/sogis_pic/documents/ch.so.arp.naturreservate/rrb/' IN reservate_dokument.dateipfad) != 0
FROM
	reservate_dokument
;
WITH foo AS
(
	SELECT
		attr
	FROM
		bar
)
INSERT INTO 
	lalelu (attr)
SELECT
	attr
FROM
	foo
;
SELECT
	CASE 
		WHEN wald IS TRUE THEN 'Wald'
		ELSE 'nicht Wald'
	END AS attr1
FROM
	waldtabelle
;
SELECT
	string_agg(code.strcode ,', ' ORDER BY code.strcode ASC) AS code
	string_agg(DISTINCT code.aname, ', ' ORDER BY code.aname) AS federfuehrung,
FROM
	isbo_prof_codenumzeichen AS code
;
SELECT
	*
FROM
	foo, bar
WHERE
	foo.geometrie && bar.geometrie
AND
	ST_Distance(foo.geometrie, bar.geometrie) = 0
;
SELECT 
   (ST_Dump(ST_Union(geometrie))).geom, 
   1 AS id
FROM 
	av_avdpool_ng.gemeindegrenzen_gemeindegrenze
;
SELECT
	gb_daten.bfs_nr AS gb_gem_bfs,
	gb_daten.kreis_nr AS gb_kreis_nr,
	grundbuchkreise.grundbuch AS gb_gemeinde,
	gb_daten.grundstueck_nr AS gb_nummer,
	gb_daten.grundstueckart AS gb_art,
	gb_daten.flaeche AS gb_flaeche,
	gb_daten.fuehrungsart AS gb_fuehrungsart,
	grundbuchkreise.nbident AS gb_nbident
FROM
	agi_av_gb_abgleich_import.gb_daten 
	JOIN av_grundbuch.grundbuchkreise
		ON 
			grundbuchkreise.gem_bfs = gb_daten.bfs_nr 
			AND 
			(
				CASE 
					WHEN grundbuchkreise.kreis_nr IS NULL
						THEN gb_daten.kreis_nr IS NULL
					ELSE grundbuchkreise.kreis_nr = gb_daten.kreis_nr
				END
			)
;
CREATE TABLE agi_grundbuchplan_pub.liegenschaften_liegenschaft (
	t_id serial NOT NULL,
	tid varchar NULL,
	liegenschaft_von varchar NULL,
	nummerteilgrundstueck varchar NULL,
	geometrie geometry NULL,
	flaechenmass float8 NULL,
	gem_bfs int4 NULL,
	los int4 NULL,
	lieferdatum date NULL,
	numpos text NULL,
	nummer varchar NULL,
	mutation bool NULL DEFAULT false,
	CONSTRAINT liegenschaften_liegenschaft_pkey PRIMARY KEY (t_id)
)
;