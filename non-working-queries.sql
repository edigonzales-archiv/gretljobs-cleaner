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