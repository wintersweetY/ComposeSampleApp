param(
    [string[]]$SkillNames = @(
        "compose-project",
        "compose-project-layout",
        "compose-project-data",
        "compose-project-hilt"
    )
)

$ErrorActionPreference = "Stop"
$repoRoot = Split-Path -Parent $PSScriptRoot
$sourceRoot = Join-Path $repoRoot ".codex/skills"
$targetRoot = Join-Path $env:USERPROFILE ".codex/skills"

New-Item -ItemType Directory -Force -Path $targetRoot | Out-Null

foreach ($skill in $SkillNames) {
    $source = Join-Path $sourceRoot $skill
    if (!(Test-Path $source)) {
        Write-Warning "跳过未找到的技能目录: $source"
        continue
    }

    $target = Join-Path $targetRoot $skill
    if (Test-Path $target) {
        Remove-Item -Recurse -Force $target
    }
    Copy-Item -Recurse -Force $source $target
    Write-Host "已安装技能: $skill"
}

Write-Host "全部技能安装完成，建议重启 Codex 会话。"
